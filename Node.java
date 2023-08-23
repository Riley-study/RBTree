 // Создаем класс Node для работы с красно-черным деревом, у которого есть следующие характеристики: значение,
 // цвет (красный либо черный), левый ребенок и правый ребенок. Переопределяем метод toString.
    public class Node {
        public int value;
        public Color color;
        public Node leftChild;
        public Node rightChild;

        @Override
        public String toString() {
            return "Node {" + "value = " + value + ", color = " + color +"}";
        }
}
