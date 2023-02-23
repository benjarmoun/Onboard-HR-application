//package com.example.onboard.configs;
//
//import com.bankconnect.entities.Agent;
//import com.bankconnect.entities.Customer;
//import com.bankconnect.entities.Request;
//import com.bankconnect.repositories.RequestRepository;
//import com.bankconnect.services.AgentService;
//import com.bankconnect.services.CustomerService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RunConfig {
//
////    @Bean
//    CommandLineRunner commandLineRunner(CustomerService cstService, AgentService agService){
//        return args -> {
//            Agent agent = new Agent("agent@bank-connect.com", "$2y$10$RVIo5RnOOfuYLoMkMK2js.MouZv.vywNuviWFofY4LMuLpQNfB/Ei");
//            Customer customer = new Customer(
//                    "saad chaay",
//                    "saad@cust.com",
//                    "$2y$10$BkhevdPWw0AuzRGcPkFR6OHLjNvS6q.fhmfBWq7wOxcXtQoQSrafm",
//                    "0615207417",
//                    "bk675034",
//                    "lafarge bouskoura",
//                    "545367823948359imGe",
//                    false
//            );
//            cstService.save(customer);
//            agService.save(agent);
//        };
//    }
//
////    @Bean
//    CommandLineRunner commandLineRunner(RequestRepository reqRepo){
//        return args -> {
//            Request req = new Request();
//            req.setTypeAccount(Enum.accType.Standard.toString());
//            req.setCustomerId(1L);
//            reqRepo.save(req);
//        };
//    }
//}
