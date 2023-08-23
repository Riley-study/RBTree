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
        return false;
    }

    // Разбалансировка дерева может произойти в трех случаях: 1 - когда у черного узла два красных ребенка;
    // 2 - когда у черного узла правый ребенок красного цвета, а левого либо нет, либо он черный;
    // 3- когда у красного узла есть ребенок красного цвета. Ниже прописано три метода, которые будут
    // использоваться в общем методе балансировки (смена цвета, правосторонний разворот, левосторонний разворот).


    // 1. Метод смены цвета меняет цвета детей на черный, а анализиуемый узел делает красным.
    private void colorSwap (Node node){
        node.leftChild.color = Color.BLACK;
        node.rightChild.color = Color.BLACK;
        node.color = Color.RED;
    }

    // 2. Метод правостороннего разворота. Метод меняет местами правого красного ребенка с анализируемым черным узлом.
    // При этом анализируемый узел становится красным левым ребенком, а бывший левый внук от правого ребенка становится
    // его правым ребенком.
    private Node rightSwap (Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;       // левый внук от правого ребенка, который поменяет родителя
        rightChild.leftChild = node;                    // анализируемый элемент меняется местами со своим правым
                                                        // ребенком и становится его левым ребенком
        node.rightChild = betweenChild;                 // левый внук становится правым ребенком анализ. элемента
        rightChild.color = node.color;                  // новый родитель перенимает цвет старого
        node.color = Color.RED;                         // новый левый ребенок становится красным
        return rightChild;
    }

    // 3. Метод левостороннего разворота. Данный метод обратный правостороннему развороту.

    private Node leftSwap (Node node){
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

// Метод ребалансировки будет срабатывать, если произойдут описанные выше три случая разбалансировки.
// Выполняем метод до тех пор, пока дерево не будет сбалансировано.
    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            // Если у анализируемого элемента есть красный правый ребенок, а левого либо нет, либо он черный, включаем
            // флаг и делаем правосторонний разворот
            if (result.rightChild != null &&
                    result.rightChild.color == Color.RED &&
                    (result.leftChild == null ||
                    result.leftChild.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            // Если у анализируемого элемента есть красный левый ребенок и красный левый внук, включаем флаг
            // и делаем левостоонний поворот
            if (result.leftChild != null &&
                    result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null &&
                    result.leftChild.leftChild.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }
            // Если оба ребенка красные, включаем флаг и меняем цвета у детей и родителя.
            if (result.leftChild != null &&
                    result.leftChild.color == Color.RED &&
                    result.rightChild != null &&
                    result.rightChild.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }
        }
        // Пока выполняется одно из трех условий и включен флаг, дерево не сбалансировано, цикл перезапустится.
        while (needRebalance);
        return result;
    }
}
