Spring Boot 使用记录：
spring获取配置文件中内容：
    @Value("${spring.profiles.active}")
    private String evn;

    @Autowired
    private Environment evnirorn;
    evnirorn.getProperty("spring.profiles.active")

    