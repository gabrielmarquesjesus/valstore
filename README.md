# Valstore - Catálogo de Skins
A aplicação segue o padrão MVC e foi gerada utilizando o Spring Initializr. 

### Tecnologias Utilizadas

- JDK 17
- Spring Boot
- JPA e Hibernate
- Thymeleaf
- PostgreSQL 15.2
- HTML, CSS e JS com Bootstrap v5.3

### Estrutura da Aplicação

`java\com\gabriel\valstore`

É o caminho onde está a classe **ValstoreApplication** que inicia a aplicação, além das controllers, services, entidades (models) e DAOs.

`resources\templates`

É o caminho onde se encontra todas as páginas da aplicação. As páginas estão separadas em pastas de acordo com o seu conteúdo.

`resources\static\css`

É o caminho onde se encontra os arquivos de estilo da aplicação.

### Mapeamento e Geração de Tabelas

As tabelas do banco de dados são geradas ou atualizadas automaticamente ao iniciar a aplicação, devido às entidades terem sido mapeadas com anotações do JPA.

![image](https://user-images.githubusercontent.com/90768594/233392725-fff794a0-8d2d-4f58-8a30-973deff91a9b.png)

### Operações do Banco de Dados

As operações de salvar, atualizar, deletar, entre outras, são realizadas através de interfaces DAO que estendem a classe JpaRepository.
```java
public interface SkinDao extends JpaRepository<Skin, Long>{
    
}
```
Essas operações são chamadas na camada service da aplicação.
```java
@Autowired
private SkinDao skinDao;

public Skin findById(Long id) throws Exception{
    return skinDao.findById(id).get();
}
```
### Navegação entre Páginas

Para navegar entre as páginas do sistema, as controllers recebem as requisições e retornam o arquivo HTML correspondente à página solicitada.
```java
@GetMapping(value = "/skinListAdmin")
public String skinsAdmin(Model model, @RequestParam(name = "sort", required = false) String sort) throws Exception {
    model.addAttribute("skins", skinService.findAllSkin(sort));
    return "catalogo/skinListAdmin";
}
```
### Validação Administrador

Para cadastrar uma nova skin no sistema, é necessário passar por uma tela de login. O método **`validaAdmin()`** é responsável por verificar se o administrador existe ou não no banco de dados.
```java
public boolean validaAdmin(Admin admin) throws Exception{
    Example example = Example.of(admin);
    Optional adminValido = adminDao.findOne(example);
    return adminValido.isEmpty() ? false : true;
}
```
Caso exista, o usuário é redirecionado para o catálogo de skins na forma de administrador, onde pode cadastrar, editar e excluir skins. Caso contrário, o usuário retorna para a tela de login com o aviso “Administrador Inválido!”.
```java
@PostMapping(value = "/validarAdmin")
public ModelAndView validarAdmin(@ModelAttribute Admin admin) throws Exception{
    boolean isValid  = adminService.validaAdmin(admin);
    ModelAndView mv = new ModelAndView();
    if(isValid){
        mv.setViewName("redirect:/skin/skinListAdmin");
        return mv;
    }else{
        mv.setViewName("admin/adminLoginForm");
        mv.addObject("aviso", "Administrador Inválido!");
        return mv; 
    }
}
```
### Imagem da Skin

Para salvar a imagem da skin, os dados da imagem são convertidos em base64 e salvos em uma coluna do banco de dados. É possível salvar somente os bytes, mas houve um problema para carregar a imagem no HTML, que foi solucionado usando o base64.
```java
public void create(Skin skin) throws Exception{
    String base64 = Base64.getEncoder().encodeToString(skin.getImagem().getBytes());
    if(!StringUtils.isEmpty(base64))
        skin.setUrlImagem(base64);
    skinDao.save(skin);
}
```
### Ordenação das Skins

Para ordenar o catálogo, uma string é passada como parâmetro da requisição contendo as informações necessárias para a ordenação, como a propriedade (valorantPoints ou raridade) e a direção (ASC ou DESC). No service, esses dados são separados e usados para realizar a busca.
```java
public List<Skin> findAllSkin(String ordenacao) throws Exception{
    Sort sort = Sort.unsorted();

    if(!StringUtils.isEmpty(ordenacao)){
        Direction direction = ordenacao.split(",")[1].equals("ASC") ? Direction.ASC : Direction.DESC;
        String prop = ordenacao.split(",")[0];
        sort = Sort.by(direction,prop);
    }
    return skinDao.findAll(sort);
}
```
