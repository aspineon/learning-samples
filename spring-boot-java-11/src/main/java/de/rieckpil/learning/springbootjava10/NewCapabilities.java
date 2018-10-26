package de.rieckpil.learning.springbootjava10;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class NewCapabilities implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Called");

		List<Integer> list = List.of(1, 334, 12, 121, 99, 1, 802);
		
		list
			.stream()
			.takeWhile(e -> e < 99)
			.forEach(System.out::println);

		IntStream
	    	.iterate(1, n -> n + 1)
	    	.takeWhile(n -> n < 10)
	    	.forEach(System.out::println);
		
	}

}
