package springfinal.pos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfinal.pos.dao.*;
import springfinal.pos.service.*;

import javax.sql.DataSource;


@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public MemberDao memberDao(){return new MemberDao(dataSource);}
    @Bean
    public MemberService memberService(){
        return  new MemberService(memberDao());
    }
    @Bean
    public ItemDao itemDao(){return new ItemDao(dataSource);}
    @Bean
    public ItemService itemService(){
        return new ItemService(itemDao());
    }
    @Bean
    public SaleDao saleDao(){return new SaleDao(dataSource);}
    @Bean
    public SaleService saleService(){
        return new SaleService(saleDao(),itemDao());
    }
    @Bean
    public StaticDao staticDao(){return new StaticDao(dataSource);}
    @Bean
    public StaticService staticService(){
        return new StaticService(itemDao(), saleDao(), staticDao());
    }

}

