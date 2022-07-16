#Zhexenov Daniyal ITSE-1908R

##Topic: Car Dealer

The project was created to visually demonstrate the interaction between the dealer and customers. 
The project was built for educational purposes, for the gradual development of Spring technology and related architectures, by manipulating the input data. 
There is also the possibility of expanding functionality, in the course of mastering new technologies.


**Functionality:**
- _Dealer_ can create a car.
- *Dealer* can remove the car.
- The *dealer* has access to all created *cars* (list).
- The *car* can calculate the fuel consumption depending on the set distance and its characteristics.
- The *car* can move.
- The *customer* can buy the *car*, which will automatically deduct the price of the car from his balance.

##Lab 1 - Inversion of Control and Dependency Injection

- Added Database classes - [Car](src/main/java/kz/iitu/itse1908r/zhexenov/database/Car.java), [CarDealer](src/main/java/kz/iitu/itse1908r/zhexenov/database/CarDealer.java), [Customer](src/main/java/kz/iitu/itse1908r/zhexenov/database/Car.java)
- For each database class *BuiderFactory* has been created
- Used Different Bean annotations : *@Component*, *@Repository*, *@Service*
- Used Different Types of Dependency Injections: *@Autowired* by Constructor and by setter

- Example:
````
@Service
public class CarDealerService {

    CarDealerRepository carDealerRepository;

    @Autowired
    public void setCarDealerRepository(CarDealerRepository carDealerRepository) {
        this.carDealerRepository = carDealerRepository;
    }
````

##Lab 2 - Spring Configuration (Beans)
- Created [*package Config*](src/main/java/kz/iitu/itse1908r/zhexenov/config) and there created config classes: [*Config*](src/main/java/kz/iitu/itse1908r/zhexenov/config/Config.java), [*ScanConfig*](src/main/java/kz/iitu/itse1908r/zhexenov/config/ScanConfig.java)
- Added [*Client*](src/main/java/kz/iitu/itse1908r/zhexenov/repository/Client.java) class
- Removed *@SpringBootApplication* annotation
- Used Annotations:
  * *@Bean*, *@Lazy*, *@Profile*
  ````
    @Profile("dev")
    @Lazy
    @Bean
    public CustomerRepository client(){
        return new Client();
    }
  ````
  * *@PropertySource*, *@Value*
  ````
  @Configuration
  @EnableAspectJAutoProxy
  @PropertySource("classpath:application.properties")
  public class Config {

    @Value("${mail}")
    String mail;
  
    ...
  ````
  * *@ComponentScan*
  ````
  @Configuration
  @ComponentScan("kz.iitu.itse1908r.zhexenov")
  public class ScanConfig {
  }
  `````
  * *@PostConstruct*, *@PreDestroy*
  ```` 
  @PostConstruct
    public void init(){
        System.out.println("Running init() method...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Running destroy() method...");
    }
  ```` 
  * *@DependsOn*
  ```` 
  @DependsOn(value = "CustomerRepository")
  public class ClientService {
    CustomerRepository customerRepository;

    @Lazy
    @Autowired
    public ClientService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    } 
  ```` 
  * *@Qualifier*
  ````
      @Autowired
    public CustomerService(@Qualifier("customerRepositoryImpl") CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
  ````

##Lab 3 - Aspect Oriented Programming
- Created [ConfigLogger](src/main/java/kz/iitu/itse1908r/zhexenov/config/ConfigLogger.java) class
- In [pom.xml](pom.xml) added dependencies:
  ````
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
    <version>2.6.3</version>
  </dependency>
  
  <dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.8.9</version>
  </dependency>
  
  <dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.6</version>
  </dependency>
  ````
- Anotations added:
  * @Aspect
  ````
  @Aspect
  @Configuration
  public class ConfigLogger {
  
   Logger log = LoggerFactory.getLogger(this.getClass());
  ````
  * *@EnableAspectJAutoProxy*.
  * *@PointCut with different arguments*.
  ````
    @Pointcut("@annotation(kz.iitu.itse1908r.zhexenov.config.TrackTime)")
    public void TrackTimeAnnotationDeclarationMethods(){
    }
  ````
  * *@After*, *@Before*, *@Around*, *@AfterThrowing*, *@AfterReturning* that logs my target classes.
  ````
    @Before("allRepoProcessingMethods() || beanCreationProcessingMethods() || customerRepoInterfaceProcessingMethods()")

    @After("allRepoProcessingMethods() || beanCreationProcessingMethods()")

    @AfterReturning(pointcut = "execution(* kz.iitu.itse1908r.zhexenov.repository.CarDealerRepositoryImpl.getListOfCars())", returning = "list")

    @AfterThrowing(pointcut = "execution(* kz.iitu.itse1908r.zhexenov.repository.CarDealerRepositoryImpl.*(..))", throwing = "error")

    @Around("TrackTimeAnnotationDeclarationMethods() || beanCreationProcessingMethods()")

  ````
- Created [TrackTime](src/main/java/kz/iitu/itse1908r/zhexenov/config/TrackTime.java) annotation in order to check execution time of target methods.
- Main class has been modified - implemented *CommandLineRunner* interface and all logic moved to overrided *run()* method.
  ````
    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(ScanConfig.class);

        SpringApplication.run(ZhexenovLab1Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\t\t ----Car class----" );
        CarService carService = applicationContext.getBean(CarService.class);
  ````

##Lab 4 - Spring JDBC
- In [pom.xml](pom.xml) added dependencies:
  ````
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jdbc</artifactId>
  </dependency>
  <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <version>42.2.10</version>
   </dependency>
  ````
- *Spring4Lab* postgresql database has been created.
- Connecting to PostgreSQL via [application.properties](src/main/resources/application.properties) file.
- Created classes:
  * [CarMapper](src/main/java/kz/iitu/itse1908/daniyal/database/CarMapper.java)
  ````
   @Component
   public class CarMapper implements RowMapper<Car> {
     @Override
     public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
     ...
   ````
  * [CarDealerMapper](src/main/java/kz/iitu/itse1908/daniyal/database/CarDealer.java)
  * [CustomerMapper](src/main/java/kz/iitu/itse1908/daniyal/database/CustomerMapper.java)
  * [CarExtractor](src/main/java/kz/iitu/itse1908/daniyal/database/CarExtractor.java)
  ````
  @Component
  public class CarExtractor implements ResultSetExtractor<Map<String, List<Map<String, Long>>>> {
     @Override
     public Map<String, List<Map<String, Long>>> extractData(ResultSet rs) throws SQLException, DataAccessException {
  ````
- Created generic *CRUDinterface* interface, that contains main methods for processing database.
  ````
  public interface CRUDinterface<T> {
    Optional<T> getById(Long id);
    int create(T t);
    int update(T t);
    int deleteById(Long id);
    List<T> getAll();
  }
  ````
- Created *runJDBC()* method where logic of main class will run
- Entity creation and data insertion
  ````
  jdbcTemplate.execute("DROP TABLE IF EXISTS Customer");
  jdbcTemplate.execute("DROP TABLE IF EXISTS Car");
  jdbcTemplate.execute("DROP TABLE IF EXISTS Car_dealer");
  
  ArrayList<CarDealer> carDealers = new ArrayList<>();
  carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(1)).setName("Toyota").build());
  carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(2)).setName("Mercedes").build());
  carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(3)).setName("BMW").build());
  ````

##Lab 5 - Hibernate
- In [pom.xml](pom.xml) added dependencies:
  ````
   <dependency>
       <groupId>org.hibernate</groupId>
       <artifactId>hibernate-ehcache</artifactId>
       <version>5.6.5.Final</version>
   </dependency>
  ````
- [MainRunner](src/main/java/kz/iitu/itse1908/daniyal/MainRunner.java) class has been created, and all main class logic has been moved here in order not to litter the main class   
  ````
  @SpringBootApplication
  @Component
  @EnableAspectJAutoProxy
  public class MainRunner implements CommandLineRunner {
  ````
- To implement the *Hibernet*, configuration beans *DataSource*, *transactionManager*, *SessionFactory* were created
  ````
  @Bean
  public DataSource dataSource() {
     ...
  @Bean
  public SessionFactory sessionFactory() throws IOException {
     ...
  @Bean
  public PlatformTransactionManager transactionManager() throws IOException {
     ...
  ````
- Using the *hibernetProperties* the *Postgresql* was connected and configured
  ````
  hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
  ````
- Annotations used:
  * *@Entity* - associates an entity in a database with a java class
  * *@Data* - automatic hidden creation of getters, setters, @toString
  * *@Cacheable* - ability to using second level cache
  ````
  hibernate.cache.use_second_level_cache=true
  hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
  hibernate.cache.use_query_cache=true
  ````
  * *@Table* - defining a class as a table with a name
  * *@NamedQueries* - creating your own complex HQL queries
  * *@Id* - marking a field as a primary key
  * *@GeneratedValue* - primary key generation
  * *@Column* - defining an attribute as a field of an entity with a name
  * *@ManyToOne* - defining a Two-Way Table Relationship
  * *@JoinColumn* - determination by which parameter the tables are linked
  * *@OrderBy* - listing collections in order
  * *@Inheritance* - class Inheritance
  * *@ManyToOne* - Defining a Two-Way Table Relationship
  * *@EnableTransactionManagement* - ability to manage transactions
  * *@Transactional* - defines the scope of a single database transaction
  
- Batch operations configured
  ````
  hibernateProp.put("hibernate.jdbc.batch_size", 10);
  ````

##Lab 6 - JPA
- In [pom.xml](pom.xml) added dependencies:
  ````
   <dependency>
       <groupId>org.hibernate</groupId>
       <artifactId>hibernate-ehcache</artifactId>
       <version>5.6.5.Final</version>
   </dependency>
   <dependency>
       <groupId>org.hibernate</groupId>
       <artifactId>hibernate-envers</artifactId>
       <version>${hibernate.version}</version>
  </dependency>
  ````
- *CarDealerRepositoryImpl*, *CarRepositoryImpl*, *CustomerRepositoryImpl* classes have been removed becouse of using JPA
- *CarDealerRepository*, *CarRepository*, *CustomerRepository* have been extended the JpaRepository<T, ID>
   ````
   public interface CarDealerRepository extends JpaRepository<CarDealer, Long> {
   ````
- Added *@Repository* annotation to the repository interfaces
- In each interface jpa provided CRUD methods are implemented
- Annotations used:
  * *@Query* - for creating own queries
  * *@Param* - to define the named parameters in query
  * *@Modifyed* - for custom modifying behaviour of queries
  ````
  @Transactional
  @Modifying
  @Query("update #{#entityName} c set c.fname = :fname, c.lname = :lname, c.balance = :balance where c.id = :id")
  void updateCustomer(@Param(value = "id") Long id, @Param(value = "fname") String fname, @Param(value = "lname") String lname,
                         @Param(value = "balance") long balance);
  ````
  * *@Audited* - to be able to check entity reporting
- Used SpEL *#{#entityName}* expression
  ````
  @Query("update #{#entityName} c set c.model = :model, c.year = :year, c.horsepower = :horsepower, c.engineCapacity = :engineCapacity" +
            ", c.carBody = :carBody, c.appetite = :appetite, c.price = :price, c.carDealer = :carDealer where c.id = :id")
  ````
- Loking have been used
  ````
  @NamedQuery(name="Car.getById",
                query="select distinct c from Car c left join fetch c.carDealer where c.id = :id", lockMode = LockModeType.READ)
  ````

##Lab 7 - Transaction Management. Validation.  
- In [pom.xml](pom.xml) added dependencies:
  ````
  <dependency>
            <groupId>com.atomikos</groupId>
            <artifactId>transactions-jdbc</artifactId>
            <version>5.0.6</version>
        </dependency>
        <dependency>
            <groupId>com.atomikos</groupId>
            <artifactId>transactions-jms</artifactId>
            <version>5.0.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.0.10.Final</version>
        </dependency>
        <dependency>
            <groupId>commons-validator</groupId>
            <artifactId>commons-validator</artifactId>
            <version>1.3.1</version>
  </dependency>
  ```` 
- Added classes:  
  * [CustomerFormatter](src/main/java/kz/iitu/itse1908/daniyal/TransformData/CustomerFormatter.java) - getting a data string, and then formatting it.   
  ````
  @Override
    public Customer parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            String[] parts = text.split(",");
            if (parts.length == 4) {
                Customer customer = new Customer();
                customer.setId(Long.parseLong(parts[0].trim()));
                customer.setFname(parts[1].trim());
                customer.setLname(parts[2].trim());
                customer.setBalance(Long.parseLong(parts[3].trim()));
                return customer;
            }
        }
        return null;
    }
  ````
  * [MyConversionService](src/main/java/kz/iitu/itse1908/daniyal/TransformData/MyConversionService.java) - helper class, for *StrToCustomerConverter* operation 
  * [StrToCustomerConverter](src/main/java/kz/iitu/itse1908/daniyal/TransformData/StrToCustomerConverter.java) - converts data from String type to Customer type.  
  * [AssertTrue](src/main/java/kz/iitu/itse1908/daniyal/Validation/AssertTrue.java) - overrided annotation that checks the validity of boolean type data, displays the message "Error!"
  ````
  @Target(value={ElementType.METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE})
  @Retention(value=RUNTIME)
  @Documented
  @Constraint(validatedBy={})
  public @interface AssertTrue {
     String message() default "ERROR!";
     Class<?>[] groups() default {};
     Class<? extends Payload>[] payload() default {};
  }
  ````
  * [CarPriceConstraint](src/main/java/kz/iitu/itse1908/daniyal/Validation/CarPriceConstraint.java) - custom annotation that validates price field in *Car* class  
  * [CarPriceValidator](src/main/java/kz/iitu/itse1908/daniyal/Validation/CarPriceValidator.java) - class containing logic of custom validation of price 
- In *Config* class have been added Distribution Transaction management logic
- *@Transactional(rollbackFor = Exception.class)* annotation have been added to rollback data if exeption trowns


##Lab 8 - Scheduling
- Added classes:
  * [ScheduledTasks](src/main/java/kz/iitu/itse1908/daniyal/Scheduling/ScheduledTasks.java) - contains scheduled task methods
- Used annotations:
  * *@EnableScheduling* - allows to use scheduled tasks
  * *@Scheduled* - run tasks according attributes 
  * *@Async* - running tasks asynchronously
  * *@EnableAsync* - allows to use *@Async* annotations
  
- @Scheduled(fixedRate = 5000) - Runs a task every 5 seconds without waiting for the previous one to finish
- @Scheduled(fixedDelay = 30000) - Waits 30sec after the previous task is verified
- @Scheduled(initialDelay = 10000, fixedDelay = 3000) - waits 10 seconds after launch, after that it fires every 3 seconds as *fixedRate*
- @Scheduled(cron = "${cron.expression}") - calls cron expression from application.properties file
- Paralell running:
  ````
      @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
  ````
  -allows to run up to 10 threads simultaneously(if you disable this bean, each thread will run its own code, and the next thread will wait.).  

##Lab 9 - REST
- In [pom.xml](pom.xml) added dependencies:
  ````
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>
  ````
- Added classes:
  * [CarController](src/main/java/kz/iitu/itse1908/daniyal/controller/CarController.java) - contains car service endpoints
  * [CarDealerController](src/main/java/kz/iitu/itse1908/daniyal/controller/CarDealerController.java) - contains car dealer service endpoints
  * [CustomerController](src/main/java/kz/iitu/itse1908/daniyal/controller/CustomerController.java) - contains customer service endpoints
  * [FileController](src/main/java/kz/iitu/itse1908/daniyal/controller/FileController.java) - contains file service endpoints
  * [FileStorageService](src/main/java/kz/iitu/itse1908/daniyal/service/FileStorageService.java) - contains service logic of uploading and downloading files
  * [FileStorageProperties](src/main/java/kz/iitu/itse1908/daniyal/config/FileStorageProperties.java) - contains file uploading prefix configuration
  * [SwaggerConfig](src/main/java/kz/iitu/itse1908/daniyal/config/SwaggerConfig.java) - contains configuration for swagger ui
  * [UploadFileResponse](src/main/java/kz/iitu/itse1908/daniyal/utils/UploadFileResponse.java) - contains type and format of file controller responses
  * [ApiResponse](src/main/java/kz/iitu/itse1908/daniyal/controller/customExeptions/ApiResponse.java) - contains custom exception logic
  * [BlogapiException](src/main/java/kz/iitu/itse1908/daniyal/controller/customExeptions/BlogapiException.java) - contains custom exception logic
  * [FileStorageException](src/main/java/kz/iitu/itse1908/daniyal/controller/customExeptions/FileStorageException.java) - contains custom exception logic
  * [MyFileNotFoundException](src/main/java/kz/iitu/itse1908/daniyal/controller/customExeptions/MyFileNotFoundException.java) - contains custom exception logic
  * [MyResourceNotFoundException](src/main/java/kz/iitu/itse1908/daniyal/controller/customExeptions/MyResourceNotFoundException.java) - contains custom exception logic
- Used annotations:
  * *@RestController* - marks the class as a controller to be able to send requests
  * *@RequestMapping* - specifies url address
  * *@ExceptionHandler* - used for custom exception handling
  * *@GetMapping* - marks get method request
  * *@PostMapping* - marks post method request
  * *@PutMapping* - marks put method request
  * *@ResponseStatus* - returns a specific message after receiving a response
  * *@DeleteMapping* - marks delete method request
  * *@PathVariable* - converts the parameter passed in the url to one of the java types
  * *@RequestBody* - defines the body of the request
  * *@EnableSwagger2* - allows you to use swagger ui
  * *@EnableConfigurationProperties* - allows you to use the configuration settings of certain classes 
- Added CRUD operations over classes:
  - Get method: 
    ![get](C:/Users/компьютер/Desktop/lab screenshots spring/9-get.png)
  - Post method:
    ![get](C:/Users/компьютер/Desktop/lab screenshots spring/9-post.png)
  - Put method:
    ![get](C:/Users/компьютер/Desktop/lab screenshots spring/9-put.png)
- Added custom exception handling functionality
- Added file uploading and downloading functionality:
- ![get](C:/Users/компьютер/Desktop/lab screenshots spring/9-files.png)
- Added swagger ui to manage endpoints:
- ![get](C:/Users/компьютер/Desktop/lab screenshots spring/9-swagger.png)

##Lab 10 - JUNIT TESTING. JMS
- In [pom.xml](pom.xml) added dependencies:
  ````
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-activemq</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.activemq</groupId>
            <artifactId>activemq-broker</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-library</artifactId>
            <version>2.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <version>2.5.0</version>
            <scope>test</scope>
        </dependency>
  ````
- Added classes:
  * [JmsConfig](src/main/java/kz/iitu/itse1908/daniyal/service/jmsService/JmsConfig.java) - contains configuration of JMS service
  * [MessageReciever](src/main/java/kz/iitu/itse1908/daniyal/service/jmsService/MessageReciever.java) - contains message listening and recieving logic
  * [JMSController](src/main/java/kz/iitu/itse1908/daniyal/controller/JMSController.java) - contains JMS functionality endpoints
- Added test classes:
  * [CarControllerTest](src/test/java/kz/iitu/itse1908/daniyal/controller/CarControllerTest.java)
  * [CarDealerControllerTest](src/test/java/kz/iitu/itse1908/daniyal/controller/CarDealerControllerTest.java)
  * [CustomerControllerTest](src/test/java/kz/iitu/itse1908/daniyal/controller/CustomerControllerTest.java)
  * [FileControllerTest](src/test/java/kz/iitu/itse1908/daniyal/controller/FileControllerTest.java)
- Added annotations:
  * *@EnableJms* - enables to browse all JmsListeners and use it to resieve a message
  * *@JmsListener* - defines the method as listener that recieves the message according destination
- Integration testing has been realized in controller classes that allows to test the REST requests
- Added JMS service that sends message to topics:
  - ![get](C:/Users/компьютер/Desktop/lab screenshots spring/10-sendJms.png)
and recieves this message:
  - ![get](C:/Users/компьютер/Desktop/lab screenshots spring/10-recieveJms.png)
- In service classes have been realized reflection testing using *Mockito* testing framework

##Lab 12. Spring MVC. Websocket technology
- In [pom.xml](pom.xml) added dependencies:
  ````
         <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-api</artifactId>
            <version>3.0.8</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
       <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-core</artifactId>
            <version>3.0.8</version>
        </dependency>
       <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-servlet</artifactId>
            <version>3.0.8</version>
        </dependency>
       <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-jsp</artifactId>
            <version>3.0.8</version>
        </dependency>
       <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-request-api</artifactId>
            <version>1.0.6</version>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
  ````
- Added classes:
  * [TilesConfig](src/main/java/kz/iitu/itse1908/daniyal/config/TilesConfig.java) - contains configuration of Tiles service
  * [WebSocketConfig](src/main/java/kz/iitu/itse1908/daniyal/config/WebSocketConfig.java) - contains configuration of socket spring
  * [WebSocketController](src/main/java/kz/iitu/itse1908/daniyal/controller/WebSocketController.java) - contains Websocket functionality endpoints
  * [Message](src/main/java/kz/iitu/itse1908/daniyal/database/Message.java) - model for socket service
- Added annotations:
  - *@Controller* - marks class as controller class for implementing mvc technology
  - *@ModelAttribute* - gets named type of data from html page
- Added files:
  - [tiles.xml](src/main/webapp/WEB-INF/tiles.xml) - here collected web views(pages), and their fragments as pathes to .jsp files
  - [template.jsp](src/main/webapp/WEB-INF/layouts/template.jsp) - here is pattern of basic html page in which we define attributes from *tiles.xml*
  - And other files that represents html pages divided along different packages:
    - *basefragments* - contains footer, header html codes that uses in every web page
    - *bodyfragments* - contains body code of every single web page that represents every model classes
- Added design using apache tiles 
- Crud operations over models:
  - View data from database: 
  - ![get](C:/Users/компьютер/Desktop/lab screenshots spring/12-getMvc.png)
  - Add data to DB using forms:
  - ![get](C:/Users/компьютер/Desktop/lab screenshots spring/12-addMvc.png)
- Used jstl tag library
- Added WebSocket service that sends message:
  - ![get](C:/Users/компьютер/Desktop/lab screenshots spring/12-socket.png)
- In service classes have been realized reflection testing using *Mockito* testing framework
