//package com.example.finalproject;
//
//import com.example.finalproject.entity.Bet;
//import com.example.finalproject.entity.User;
//import com.example.finalproject.repository.BetRepository;
//import com.example.finalproject.repository.VisitorRepository;
//import com.example.finalproject.service.bet.BetService;
//import com.example.finalproject.service.bet.BetServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//
//@ContextConfiguration(classes = BetServiceImpl.class)
//@RunWith(MockitoJUnitRunner.class)
//public class BetServiceTest {
//    @Test
//    public void checkWork(){
//        BetService betService = Mockito.mock(BetServiceImpl.class);
//        VisitorRepository visitorRepository = Mockito.mock(VisitorRepository.class);
//        Assertions.assertNotNull(betService.addBet(
//                visitorRepository.findByEmail("TestingUser").getEmail()
//                ,100, "Team1", 1));
//    }
//}
