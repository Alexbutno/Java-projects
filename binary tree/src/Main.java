public class Main {
    public static void main(String[] args) {
        try {
            Tree<Double> tree = new Tree<>(80.3);
            tree.insert(30.443);
            tree.insert(100.032);
            tree.insert(10.05);
            tree.insert(60.1);
            tree.insert(40.4);
            tree.insert(70.2);
            tree.insert(140.1);
            tree.insert(130.4);
            tree.insert(45.4);
            tree.insert(46.4);

            tree.deleteElement(30.443);
            tree.deleteElement(100.032);
            tree.deleteElement(130.4);
            //tree.insert(60.1);
            tree.printLeftTopRight();
            System.out.println("\n");
            tree.printTopLeftRight();
            System.out.println("\n");
            tree.printLeftRightTop();
            System.out.println("\n");
            /*
            Person a = new Person("alex", 180, 18, 78);
            Person b = new Person("arti", 179, 18, 78);
            Person c = new Person("folba", 182, 18, 78);
            Person d = new Person("fffffff", 182, 18, 78);
            Person f = new Person("car", 182, 18, 78);
            Tree<Person> extree = new Tree<>(a);
            extree.insert(b);
            extree.insert(c);
            extree.insert(d);
            extree.insert(f);
            extree.printLeftTopRight();
             */

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static class Person implements Comparable<Person> {
        String name;
        int height;
        int age;
        double weight;

        Person(String name, int height, int age, double weight) {
            this.name = name;
            this.height = height;
            this.age = age;
            this.weight = weight;
        }


        public String toString() {
            return "Name is " + name + ",height is " + height + ",age is " + age + ",weight is " + weight;
        }


        public int compareTo(Person obj) {
            if (this.age+ this.name.length()+this.height+this.age > obj.age+ obj.name.length()+obj.height+obj.age) {
                return 1;
            }
            else if (this.age+ this.name.length()+this.height+this.age < obj.age+ obj.name.length()+obj.height+obj.age) {

                return -1;
            }
            else {
                return 0;
            }
        }
    }

    static class Tree<T extends Comparable<T>> {
        private class Node {
            T value;
            Node left;
            Node right;

            Node(T value) {
                this.value = value;
                left = right = null;
            }

        }
        Node root;

        Tree(T root) {
            this.root = new Node(root);
        }

        boolean search(T value) {
            Node currRoot = root;
            while (currRoot != null) {
                if (value.compareTo(currRoot.value)==0) {
                    return true;
                } else {
                    if (value.compareTo(currRoot.value)<0) {
                        currRoot = currRoot.left;
                    } else
                        currRoot = currRoot.right;
                }
            }
            return false;
        }

        public void insert(T value) {
            root = insert(root, value);
        }

        private Node insert(Node node, T value) {
            if (!search(value)) {
                Node newNode = new Node(value);
                if (node == null) {
                    node = newNode;
                    return node;
                } else if (node.value.compareTo(value)>0) {
                    node.left = insert(node.left, value);
                } else if (node.value.compareTo(value)<0) {
                    node.right = insert(node.right, value);
                }
                return node;
            }
            throw new IllegalArgumentException("element "+value+ " is  already in tree");
        }

        public void printTopLeftRight() {
            printTopLeftRight(root);

        }

        private void printTopLeftRight(Node node) {
            if (node != null) {
                System.out.print(node.value+"; ");
                printTopLeftRight(node.left);
                printTopLeftRight(node.right);
            }

        }

        public void printLeftTopRight() {
            printLeftTopRight(root);

        }

        private void printLeftTopRight(Node node) {
            if (node != null) {
                printLeftTopRight(node.left);
                System.out.println(node.value+"; ");
                printLeftTopRight(node.right);
            }

        }

        public void printLeftRightTop() {
            printLeftRightTop(root);

        }

        private void printLeftRightTop(Node node) {
            if (node != null) {
                printLeftRightTop(node.left);
                printLeftRightTop(node.right);
                System.out.print(node.value+"; ");
            }

        }

        private Node oneRightAllLeft(Node node) {
            Node parent = node;
            Node last = node;
            Node current = node.right;
            while (current != null) //
            {
                parent = last;
                last = current;
                current = current.left;
            }
            if (last != node.right) {
                parent.left = last.right;
                last.right = node.right;
            }
            last.left = node.left;
            return last;
        }

        public void deleteElement(T value) {
            if (search(value)) {
                Node currentNode = root;
                Node parentNode = root;
                boolean isLeftChild = true;
                while (currentNode.value.compareTo(value)!=0) {
                    parentNode = currentNode;
                    if (currentNode.value.compareTo(value)<0) {
                        if (currentNode.right != null) {
                            isLeftChild = false;
                            currentNode = currentNode.right;
                        }
                    } else {
                        if (currentNode.left != null) {
                            isLeftChild = true;
                            currentNode = currentNode.left;
                        }
                    }
                }
                if (currentNode.left == null && currentNode.right == null) {
                    if (currentNode == root) {
                        root = null;
                    } else if (isLeftChild) {
                        parentNode.left = null;
                    } else {
                        parentNode.right = null;
                    }
                } else if (currentNode.right == null) {
                    if (currentNode == root) {
                        root = currentNode.left;
                    } else if (isLeftChild) {
                        parentNode.left = currentNode.left;
                    } else {
                        parentNode.right = currentNode.left;
                    }
                } else if (currentNode.left == null) {
                    if (currentNode == root) {
                        root = currentNode.right;
                    } else if (isLeftChild) {
                        parentNode.left = currentNode.right;
                    } else {
                        parentNode.right = currentNode.right;
                    }
                } else {
                    Node temp = oneRightAllLeft(currentNode);
                    if (currentNode == root)
                        root = temp;
                    else if (isLeftChild)
                        parentNode.left = temp;
                    else
                        parentNode.right = temp;
                }
            } else {
                throw new IllegalArgumentException("element " + value + " is not in tree");
            }
        }
    }
}