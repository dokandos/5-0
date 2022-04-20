package com.ScoreReceiver.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//No need for this class as there are no custom methods
@DataJpaTest
public class UserScoreRepositoryTest {

    @Autowired
    private UserScoreRepository userScoreRepository;

}
