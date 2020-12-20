/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.Statistics;

/**
 *
 * @author rajanssi
 */
public class StatisticsTest {
    
    Statistics statistics;
  
    @Before
    public void setUp(){
        statistics = new Statistics();
        statistics.setAll(2, 3, 2, 4);
    }
    
    @Test
    public void returnsCorrectCount(){
        String count = "2";
        
        assertEquals(count, statistics.getCount());
    }
    
    @Test
    public void returnsCorrectAVG(){
        String avg = "0 min 3 sec";
        
        assertEquals(avg, statistics.getAvg());
    }
    
    @Test
    public void returnsCorrectMIN(){
        String min = "0 min 2 sec";
        
        assertEquals(min, statistics.getMin());
    }
    
    @Test
    public void returnsCorrectMax(){
        String max = "0 min 4 sec";
        
        assertEquals(max, statistics.getMax());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
