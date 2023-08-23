 // Создаем класс Node для работы с красно-черным деревом, у котоого есть следующие характеристики: значение,
 // цвет (красный либо черный), левый ребенок и правый ребенок. Переопределяем метод toString.
    public class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node {" + "value =" + value + ", color = " + color + "}";
        }
}
