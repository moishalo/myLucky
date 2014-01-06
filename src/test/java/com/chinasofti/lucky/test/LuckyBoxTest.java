package com.chinasofti.lucky.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chinasofti.lucky.box.LuckyBox;
import com.chinasofti.lucky.ld.DefaultLuckyDogProvider;
import com.chinasofti.lucky.ld.LuckyDog;
import com.chinasofti.lucky.ld.LuckyDogProvider;

/**
 * @Title: LuckyBoxTest.java
 * @Package myLucky
 * @Description: 测试luckybox
 * @author moishalo.zhang moishalo.zhang@gmail.com
 * @date 2014年1月5日 下午5:40:17
 * @version V1.0
 */
public class LuckyBoxTest {

	LuckyBox box;
	LuckyDogProvider provider;

	InputStream in;
	InputStream remove;
	
	LuckyDog luckyDogToRemove;

	@Before
	public void setUp() throws Exception {
		box = LuckyBox.getLuckyBox();
		provider = new DefaultLuckyDogProvider();

		in = this.getClass().getClassLoader()
				.getResourceAsStream("luckydogs.properties");
		remove = this.getClass().getClassLoader()
				.getResourceAsStream("luckydogs_remove.properties");
		
		luckyDogToRemove = new LuckyDog();
		luckyDogToRemove.setCode("4B9DF231-2DF6-1949-0A6A-5376E5EDC174");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shuffleTest() {
		LuckyBox box = LuckyBox.getLuckyBox();

		int[] index = new int[10];
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			ran.setSeed(System.nanoTime());
			index[i] = Math.abs(ran.nextInt()) % 100;
		}

		box.addAll(provider.get(in));

		List<LuckyDog> origin = new ArrayList<LuckyDog>();
		List<LuckyDog> now = new ArrayList<LuckyDog>();
		for (int i = 0; i < 10; i++) {
			origin.add(box.getLuckyDogs().get(index[i]));
		}
		box.print();
		System.out.println("开始摇一摇");
		box.shuffle();
		System.out.println("摇完了");
		for (int i = 0; i < 10; i++) {
			now.add(box.getLuckyDogs().get(index[i]));
		}
		box.print();
		
		for (int i = 0; i < 10; i++) {
			assertNotEquals(origin.get(i),now.get(i));
		}
	}
	
	@Test
	public void removeAllTest(){
		box.addAll(provider.get(in));
		box.shuffle();
		box.print();
		
		List<LuckyDog> allremove = provider.get(remove);
		box.removeAll(allremove);
		box.shuffle();
		box.print();
		
		for(LuckyDog luckyDog : allremove){
			assertFalse(box.getLuckyDogs().contains(luckyDog));
		}
	}
	
	@Test
	public void removeOne(){
		box.addAll(provider.get(in));
		box.shuffle();
		box.print();
		
		assertEquals(100, box.getLuckyDogs().size());
		
		box.remove(luckyDogToRemove);
		
		box.print();
		
		assertEquals(99, box.getLuckyDogs().size());
		
		assertFalse(box.getLuckyDogs().contains(luckyDogToRemove));
	}

}
