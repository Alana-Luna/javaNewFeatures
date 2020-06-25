package br.com.techtalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainDemo {

	public static void main(String[] args) {

		// Java 9
		executeTakeWhile();
		executeDropWhile();
		executeIterate();
		executeOfNullable();

		// Java 10
		testVarType();
		testCopyOf();
		testUnmodifiable();
		testOrElseThrow();

		// Java 11
		testIsBlank();
		testLines();
		testStrip();
		testRepeat();
		testVarWithLambda();

		// Java 12
//		testTeeingCollector();

	}

	public static void executeTakeWhile() {

		Stream<Integer> stream = Stream.of(2, 4, 6, 8, 9, 10, 12);
		List<Integer> list = stream.takeWhile(n -> n % 2 == 0).collect(Collectors.toList());
		System.out.println("# Executando takeWhile(): " + list);
	}

	public static void executeDropWhile() {

		Stream<Integer> stream = Stream.of(2, 4, 6, 8, 9, 10, 12);
		List<Integer> list = stream.dropWhile(n -> n % 2 == 0).collect(Collectors.toList());
		System.out.println("# Executando dropWhile(): " + list);

	}

	public static void executeIterate() {

		IntStream.iterate(3, x -> x < 10, x -> x+ 3).forEach(System.out::println);

	}

	private static void executeOfNullable() {

		String str = null;
		Stream.ofNullable(str).forEach(System.out::println);

		str = "Techtalk RD";
		Stream.ofNullable(str).forEach(System.out::println);

	}

	private static void testVarType() {

		Map<String, Integer> numberMapping = new HashMap<>();

		numberMapping.put("a", 1);
		numberMapping.put("b", 2);

		System.out.println("Map sem var: " + numberMapping);

		var numberMappingTypeVar = new HashMap<String, Integer>();

		numberMappingTypeVar.put("a", 1);
		numberMappingTypeVar.put("b", 2);

		System.out.println("Map com var: " + numberMappingTypeVar);

	}

//	public static void testVarTypeFailed() {
//			
//		var n; // erro: cannot use 'var' on variable without initializer
//			
//		var emptyList = null; // erro: variable initializer is 'null'
//			
//		var p = (String s) -> s.length() > 10; // erro: lambda expression needs an explicit target-type
//		
//		var arr = { 1, 2, 3 }; // erro: array initializer needs an explicit target-type
//	}

	public static void testCopyOf() {

		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);

		List<Integer> copyList = List.copyOf(list);

		System.out.println("Lista copiada: " + copyList.toString());

		try {
			copyList.add(40);
		} catch (Exception e) {
			System.out.println("Erro ao tentar alterar lista: " + e);
		}
	}

	public static void testUnmodifiable() {

		List<Integer> list = new ArrayList<Integer>();
		list.add(11);
		list.add(16);
		list.add(20);

		List<Integer> evenList = list.stream()
				.filter(i -> i % 2 == 0)
				.collect(Collectors.toUnmodifiableList());

		System.out.println("Lista: " + evenList.toString());

		try {
			evenList.add(8);
		} catch (Exception e) {
			System.out.println("Erro ao tentar alterar lista: " + e);
		}

	}

	private static void testOrElseThrow() {

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(5);

		try {
			list.stream()
					.filter(i -> i % 2 == 0)
					.findFirst()
					.orElseThrow();
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

	private static void testIsBlank() {

		System.out.println(" ".isBlank()); // true

		String title = "Techtalk";
		System.out.println(title.isBlank()); // false

		String emptyString = "";
		System.out.println(emptyString.isBlank()); // true

	}

	private static void testLines() {

		String message = "Techtalk\nRD\n2020";
		System.out.println(message);
		System.out.println(message.lines().collect(Collectors.toList()));

	}

	private static void testStrip() {

		String message = "  Novidade Java 10  ";
		System.out.println("#" + message + "#");
		System.out.println("#" + message.strip() + "#");
		System.out.println("#" + message.stripLeading() + "#");
		System.out.println("#" + message.stripTrailing() + "#");

	}

	private static void testRepeat() {

		String message = "Techtalk RD\n".repeat(2);
		System.out.println(message);

	}

	private static void testVarWithLambda() {

		IntStream.of(1, 2, 3, 5, 6, 7)
				.filter((var i) -> i % 3 == 0)
				.forEach(System.out::println);

		// similar:
		IntStream.of(1, 2, 3, 5, 6, 7)
				.filter(i -> i % 3 == 0)
				.forEach(System.out::println);
	}

//	private static void testTeeingCollector() {
//		var result = Stream.of(
//
//				// classe Guest(String name, boolean participating, Integer participantsNumber)
//				new Guest("Giovanna", true, 3),
//				new Guest("David", false, 2),
//				new Guest("Aurora", true, 6))
//
//				.collect(Collectors.teeing(
//
//						// primeiro colecionador, selecionamos apenas quem confirmou a participação
//						Collectors.filtering(Guest::isParticipating,
//
//								// queremos coletar apenas o primeiro nome em uma lista
//								Collectors.mapping(o -> o.name, Collectors.toList())),
//
//						// segundo colecionador, queremos o número total de participantes
//						Collectors.summingInt(Guest::getParticipantsNumber),
//
//						// mesclamos os coletores em um novo objeto
//						EventParticipation::new));
//
//		// Participação no evento {convidados = [Giovanna, Aurora], total de
//		// participantes = 11 }
//		System.out.println(result);
//	}

}