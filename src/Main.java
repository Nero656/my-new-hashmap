
void main() {
    Scanner scanner = new Scanner(System.in);

    MyCustomHashMap<String, String> map = new MyCustomHashMap<>(
            new MyHashMapStrategy<>(),
            10
    );

    for (int i = 0; i <= 15; i++) {
        map.put(String.valueOf(i), "value = " + i);
    }

    map.display();

    boolean validInput = false;

    while (!validInput) {
        try {
            System.out.println("========Chose option========");
            System.out.println("1: get value");
            System.out.println("2: remove value");
            int option = scanner.nextInt();
            scanner.nextLine();

            System.out.print("введите ключ: ");
            String key = scanner.nextLine();

            switch (option) {
                case 1 -> {
                    map.get(key);
                    validInput = true;
                }
                case 2 -> {
                    map.remove(key);
                    map.display();
                    validInput = true;
                }
                default -> {
                    System.out.println("Неверная опция! Доступно: 1 или 2");
                    System.out.println("Попробуйте снова...");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Введите число 1 или 2");
            System.out.println("Попробуйте снова...");
        }


    }
    scanner.close();
}
