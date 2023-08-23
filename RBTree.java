public class RBTree {
    private Node root;
    public boolean addNote (Node node, int value){
        // Красно-черное дерево является частным случаем бинарного дерева. Одна из особенностей бинарного дерева
        // в том, что все значения должны быть уникальными. Поэтому, если добавляемый узел уже существует, метод
        // возвращает false, создать новое такое же значение будет нельзя.

        if (node.value == value) {
            return false;
        } else {
        //  Далее проверяем, если значение в проверяемом узле больше искомого значения, алгоритм начинает
        //  обходить левую часть дерева рекурсивно проваливаясь ниже, чтобы найти свободное место для вставки нового
        //  элемента и убедиться в отсутствии дублирования значений в дереве. При выходе из рекусии в случае
        //  разбалансировки дерева включается метод rebalance.
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNote(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                } else {
        // когда пройдено все дерево до конца, повторов не найдено, создается новый красный узел, которому присваивается
        // значение value
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
            // В оставшемся случае, когда значение нового узла больше проверяемого, алгоритм проходит к анализу
            // правого ребенка аналогичным образом. Когда у анализируемого узла нет правого ребенка, создаем новый
            // красный узел, присваеваем ему нужное значение. За счет рекурсии алгоритм каждый раз идет по дереву
            // в соответствующее место в зависимости отзначения добавляемого элемента
                if (node.rightChild != null){
                    boolean result = addNote(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }

    }

    private Node rebalance(Node leftChild) {
    }
}
