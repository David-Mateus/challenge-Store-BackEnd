//package tech.davidmateus.storeApi.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Value("${cors.originPatterns:default}") // vai le o application.yml e procura por essa propriedade
//    private String corsOriginPatterns = "";
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //seta as origins permitidas
//        var allowedOrigins = corsOriginPatterns.split(",");
//        registry.addMapping("/**")
//                .allowedMethods("*")
//                .allowedOrigins(allowedOrigins)
//                .allowCredentials(true);
//    }
//}
