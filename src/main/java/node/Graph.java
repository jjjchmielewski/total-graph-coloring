package node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class Graph {

  //Liczba wierzchołków w grafie
  private int numberOfVertices;

  //Połączenia danego wierzchołka
  private LinkedList<Integer> listOfEdges[];

  Graph(int number) {
    numberOfVertices = number;
    listOfEdges = new LinkedList[number];

    for (int i = 0; i < number; ++i) {
      listOfEdges[i] = new LinkedList();
    }
  }

  void addEdge(int vertexA, int vertexB) {

    //Ponieważ graf nie jest skierowany - dodajemy połączenia w dwie strony
    listOfEdges[vertexA].add(vertexB);
    listOfEdges[vertexB].add(vertexA);
  }

  void greedyColoring() {

    //Tablica zawiera kolory wszystkich wierzchołków
    int colorTable[] = new int[numberOfVertices];

    //Domyślnie każdy z nich ma wartość -1
    Arrays.fill(colorTable, -1);

    //Na starcie możemy pokolorować dowolny wierzchołek
    colorTable[0] = 0;

    //Tablica dostępnych kolorów mówi nam o tym, które kolory są dostępne (sąsiedzi ich nie mają)
    boolean availableColors[] = new boolean[numberOfVertices];

    //Domyślnie wszystkie kolory są dostępne
    Arrays.fill(availableColors, true);

    //Ta pętla podróżuje po wszystkich wierzchołkach - zaczynamy od drugiego, bo pierwszy jest już pokolorowany
    for (int vertex = 1; vertex < numberOfVertices; vertex++) {

      //Iterator pomoże nam w podróżowaniu po sąsiadach
      Iterator<Integer> iterator = listOfEdges[vertex].iterator();

      while (iterator.hasNext()) {

        //Sprawdzamy, który wierzchołek jest sąsiadem
        int neighbour = iterator.next();

        //Jeśli sąsiad zajął już dany kolor, to oznaczamy go jako niedostępny
        if (colorTable[neighbour] != -1) {
          availableColors[colorTable[neighbour]] = false;
        }
      }

      //Wybieramy pierwszy dostępny kolor
      int color;
      for (color = 0; color < numberOfVertices; color++) {
        if (availableColors[color]) {
          break;
        }
      }

      //Przypisujemy go do tablicy
      colorTable[vertex] = color;

      //Przywracamy tablicę kolorów do stanu domyślnego
      Arrays.fill(availableColors, true);
    }

    //Na koniec wypisujemy wszystkie krawędzie wraz z ich kolorami
    for (int vertex = 0; vertex < numberOfVertices; vertex++) {
      System.out.println("Krawędź " + vertex + " --->  Kolor " + colorTable[vertex]);
    }
  }

  // Driver method
  public static void main(String args[]) {
    Graph g1 = new Graph(5);
    g1.addEdge(0, 1);
    g1.addEdge(0, 2);
    g1.addEdge(1, 2);
    g1.addEdge(1, 3);
    g1.addEdge(2, 3);
    g1.addEdge(3, 4);
    System.out.println("Kolorowanie grafu 1");
    g1.greedyColoring();

    System.out.println();
    Graph g2 = new Graph(5);
    g2.addEdge(0, 1);
    g2.addEdge(0, 2);
    g2.addEdge(1, 2);
    g2.addEdge(1, 4);
    g2.addEdge(2, 4);
    g2.addEdge(4, 3);
    System.out.println("Kolorowanie grafu 2");
    g2.greedyColoring();
  }
}

// Podczas pisania tego kodu wspomogliśmy się kodem autorstwa "Aakash Hasija"
// This code is contributed by Aakash Hasija
