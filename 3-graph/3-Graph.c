// Answer.c
// Communication and Computer System Engineering Laboratory II
// Eiji Ogiwara

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <limits.h>


// The basic type of vertices

typedef int Vertex;


const char *readVertex(Vertex *v, const char *str)
{
    int off;
    sscanf(str, " %d %n", v, &off);
    return str + off;
}

void showVertex(Vertex v)
{
    printf("%d", v);
}

// Lists of vertices

typedef struct {
    unsigned int len;
    Vertex *sto;
} ListOfVertices;

int isEqListOfVertices(const ListOfVertices *vs, const ListOfVertices *us)
{
    if (vs->len != us->len) return 0;
    for (unsigned int i = 0; i < vs->len; i ++) {
        if (vs->sto[i] != us->sto[i]) return 0;
    }
    return 1;
}

void copyListOfVertices(ListOfVertices *vs, const ListOfVertices *us)
{
    vs->len = us->len;
    vs->sto = malloc(vs->len * sizeof(*vs->sto));
    for (unsigned int i = 0; i < vs->len; i ++) {
        vs->sto[i] = us->sto[i];
    }
}

void emptyListOfVertices(ListOfVertices *vs)
{
    vs->len = 0;
    vs->sto = malloc(sizeof(*vs->sto));
}

int isNullListOfVertices(const ListOfVertices *vs)
{
    return vs->len == 0;
}

void appendListOfVertices(ListOfVertices *vs, Vertex v)
{
    vs->len ++;
    vs->sto = realloc(vs->sto, vs->len * sizeof(*vs->sto));
    vs->sto[vs->len - 1] = v;
}

Vertex removeAtHeadListOfVertices(ListOfVertices *vs)
{
    Vertex v;

    assert(vs->len > 0);
    v = vs->sto[0];

    vs->len --;
    for (unsigned int i = 0; i < vs->len; i ++) {
        vs->sto[i] = vs->sto[i + 1];
    }
    vs->sto = realloc(vs->sto, vs->len * sizeof(*vs->sto));

    return v;
}

void filterListOfVertices(ListOfVertices *vs, int (*predicate)(Vertex), const ListOfVertices *us)
{
    emptyListOfVertices(vs);
    for (unsigned int i = 0; i < us->len; i ++) {
        Vertex v = us->sto[i];
        if (predicate(v)) appendListOfVertices(vs, v);
    }
}

const char *readListOfVertices(ListOfVertices *vs, const char *str)
{
    int off;

    sscanf(str, " %d %n", &vs->len, &off); str += off;
    vs->sto = malloc(vs->len * sizeof(*vs->sto));
    for (unsigned int i = 0; i < vs->len; i ++) {
        str = readVertex(&vs->sto[i], str);
    }

    return str;
}

void showListOfVertices(const ListOfVertices *vs)
{
    printf("%d", vs->len);
    for (unsigned int i = 0; i < vs->len; i ++) {
        printf(" ");
        showVertex(vs->sto[i]);
    }
}

void showListOfVertices2(const ListOfVertices *vs)
{
    printf("[");
    if (vs->len > 0) showVertex(vs->sto[0]);
    for (unsigned int i = 1; i < vs->len; i ++) {
        printf(",");
        showVertex(vs->sto[i]);
    }
    printf("]");
}

void freeListOfVertices(ListOfVertices *vs)
{
    free(vs->sto);
}

// Graphs in adjacency list representation

typedef struct {
    Vertex min,max;
    ListOfVertices *adj;
} Graph;

ListOfVertices *adj(const Graph *g, Vertex v)
{
    return g->adj + (v - g->min);
}

int isEqGraph(const Graph *g, const Graph *h)
{
    if (g->min != h->min || g->max != h->max) return 0;
    for (Vertex v = g->min; v <= g->max; v ++) {
        if (!isEqListOfVertices(adj(g, v), adj(h, v))) return 0;
    }
    return 1;
}

void copyGraph(Graph *g, const Graph *h)
{
    g->min = h->min;
    g->max = h->max;
    g->adj = malloc((g->max - g->min + 1) * sizeof(*g->adj));
    for (Vertex v = g->min; v <= g->max; v ++) {
        copyListOfVertices(adj(g, v), adj(h, v));
    }
}

const char *readGraph(Graph *g, const char *str)
{
    int off;

    sscanf(str, " %d \n%n", &g->min, &off); str += off;
    sscanf(str, " %d \n%n", &g->max, &off); str += off;
    g->adj = malloc((g->max - g->min + 1) * sizeof(*g->adj));
    for (Vertex v = g->min; v <= g->max; v ++) {
        str = readListOfVertices(adj(g, v), str);
        sscanf(str, " \n%n", &off);
        str += off;
    }

    return str;
}

void showGraph(const Graph *g)
{
    printf("%d\n", g->min);
    printf("%d\n", g->max);
    for (Vertex v = g->min; v <= g->max; v ++) {
        showListOfVertices(adj(g, v));
        printf("\n");
    }
}

void freeGraph(Graph *g)
{
    for (Vertex v = g->min; v <= g->max; v ++) {
        freeListOfVertices(adj(g, v));
    }
    free(g->adj);
}

// The basic type of edges

typedef struct {
    Vertex s;
    Vertex t;
} OrderedPairOfVertices;

typedef OrderedPairOfVertices Edge;

int isEqEdge(const Edge *e, const Edge *f)
{
    return e->s == f->s && e->t == f->t;
}

const char *readEdge(Edge *e, const char *str)
{
    int off;
    sscanf(str, " %d -> %d %n", &e->s, &e->t, &off);
    return str + off;
}

void showEdge(const Edge *e)
{
    printf("%d -> %d", e->s, e->t);
}

// Lists of edges

typedef struct {
    unsigned int len;
    Edge *sto;
} ListOfEdges;

int isEqListOfEdges(const ListOfEdges *es, const ListOfEdges *fs){   // test if *es == *fs
    if (es->len != fs->len) {
        return 0;
    }

    for (unsigned int i = 0; i < es->len; i++) {
        if (!isEqEdge(&es->sto[i], &fs->sto[i])) {
            return 0;
        }
    }

    return 1;
};

void copyListOfEdges(ListOfEdges *es, const ListOfEdges *fs){   // copy fs to es
    es->len = fs->len;
    es->sto = malloc(es->len * sizeof(*es->sto));
    for (unsigned int i = 0; i < es->len; i++) {
        es->sto[i] = fs->sto[i];
    }
};

int isNullListOfEdges(const ListOfEdges *es){ // test if *es == []
    return es->len == 0;
};

const char *readListOfEdges(ListOfEdges *es, const char *str) { // read es from str
    int off;

    sscanf(str, " %d %n", &es->len, &off); str += off;
    es->sto = malloc(es->len * sizeof(*es->sto));
    for (unsigned int i = 0; i < es->len; i ++) {
        str = readEdge(&es->sto[i], str);
    }

    return str;
};

void showListOfEdges(const ListOfEdges *es) {
    printf("%d\n", es->len);
    for (unsigned int i = 0; i < es->len; i ++) {
        showEdge(&es->sto[i]);
        printf("\n");
    }
};

void freeListOfEdges(ListOfEdges *es) { // free the memory used by es
    free(es->sto);
    es->sto = NULL;
    es->len = 0;
};


// The vertices of a graph
void vertices(ListOfVertices *vs, const Graph *g)
{
    unsigned int i;
    Vertex v;

    vs->len = g->max - g->min + 1;
    vs->sto = malloc(vs->len * sizeof(*vs->sto));
    i = 0; v = g->min;
    while (v <= g->max) {
        vs->sto[i ++] = v ++;
    }
}

// The edges of a graph
void edges(ListOfEdges *es, const Graph *g) {
    es->len = 0;
    es->sto = malloc(0); // 修正: 初期サイズを 0 に設定
    for (Vertex v = g->min; v <= g->max; v++) {
        ListOfVertices *adjVertices = adj(g, v);
        for (unsigned int i = 0; i < adjVertices->len; i++) {
            es->len++;
            es->sto = realloc(es->sto, es->len * sizeof(*es->sto));
            es->sto[es->len - 1] = (Edge){ v, adjVertices->sto[i] };
        }
    }
}

// The outdegree of a vertex of a graph
unsigned int outdegree(const Graph *g, Vertex v)
{
    return adj(g, v)->len;
}

// The indegree of a vertex of a graph
unsigned int indegree(const Graph *g, Vertex v) {
    unsigned int count = 0;
    ListOfEdges es;
    edges(&es, g);
    for (unsigned int i = 0; i < es.len; i++) {
        if (es.sto[i].t == v) {
            count++;
        }
    }
    freeListOfEdges(&es);
    return count;
}

int isaTopSort(const ListOfVertices *vs, const Graph *g) {
    Graph h;
    ListOfVertices us;

    copyGraph(&h, g);
    copyListOfVertices(&us, vs);

    while (!isNullListOfVertices(&us)) {
        Vertex u = removeAtHeadListOfVertices(&us);
        if (indegree(&h, u) != 0) return 0;
        emptyListOfVertices(adj(&h, u));
    }

    return 1;
}

int topSort(ListOfVertices *L, const Graph *G) {
    ListOfVertices S;
    emptyListOfVertices(L);
    emptyListOfVertices(&S);

    // 入次数を計算するための配列を初期化
    int *inDegree = calloc(G->max - G->min + 1, sizeof(int));
    for (Vertex v = G->min; v <= G->max; v++) {
        inDegree[v - G->min] = indegree(G, v);
    }

    // 初期化：Sに入次数が0の全頂点を追加
    for (Vertex v = G->min; v <= G->max; v++) {
        if (inDegree[v - G->min] == 0) {
            appendListOfVertices(&S, v);
        }
    }

    while (!isNullListOfVertices(&S)) {
        Vertex u = removeAtHeadListOfVertices(&S);
        appendListOfVertices(L, u);
        ListOfVertices *adjVertices = adj(G, u);
        for (unsigned int i = 0; i < adjVertices->len; i++) {
            Vertex v = adjVertices->sto[i];
            inDegree[v - G->min]--;
            if (inDegree[v - G->min] == 0) {
                appendListOfVertices(&S, v);
            }
        }
    }

    free(inDegree);

    ListOfEdges es;
    edges(&es, G);
    int isDag = isNullListOfEdges(&es);
    freeListOfEdges(&es);

    return isDag;
}

// プロトタイプ宣言
Vertex findRoot(const Graph *g);

// Test if a list of vertices represents an isomorphism between two rooted term DAGs
int isanIsomorphism(const ListOfVertices *phi, const Graph *g, const Graph *h) {
    if (g->min != h->min || g->max != h->max || phi->len != g->max - g->min + 1) {
        return 0;
    }

    // 頂点の写像を作成
    Vertex *mapping = malloc((g->max - g->min + 1) * sizeof(Vertex));
    for (unsigned int i = 0; i < phi->len; i++) {
        mapping[g->min + i] = phi->sto[i];
    }

    // 各頂点の隣接リストが対応しているかを確認
    for (Vertex v = g->min; v <= g->max; v++) {
        ListOfVertices *adjG = adj(g, v);
        ListOfVertices *adjH = adj(h, mapping[v]);

        if (adjG->len != adjH->len) {
            free(mapping);
            return 0;
        }

        for (unsigned int i = 0; i < adjG->len; i++) {
            int found = 0;
            for (unsigned int j = 0; j < adjH->len; j++) {
                if (mapping[adjG->sto[i]] == adjH->sto[j]) {
                    found = 1;
                    break;
                }
            }
            if (!found) {
                free(mapping);
                return 0;
            }
        }
    }

    free(mapping);
    return 1;
}

// Function to find an isomorphism between two rooted term DAGs
int isomorphism(ListOfVertices *phi, const Graph *g, const Graph *h) {
    if (g->min != h->min || g->max != h->max) {
        return 0;
    }

    Vertex rootG = findRoot(g);
    Vertex rootH = findRoot(h);
    if (rootG == INT_MIN || rootH == INT_MIN) {
        return 0;
    }

    Vertex *mapping = malloc((g->max - g->min + 1) * sizeof(Vertex));
    for (Vertex v = g->min; v <= g->max; v++) {
        mapping[v] = -1;
    }

    ListOfVertices stackG, stackH;
    emptyListOfVertices(&stackG);
    emptyListOfVertices(&stackH);
    appendListOfVertices(&stackG, rootG);
    appendListOfVertices(&stackH, rootH);

    while (!isNullListOfVertices(&stackG)) {
        Vertex vG = removeAtHeadListOfVertices(&stackG);
        Vertex vH = removeAtHeadListOfVertices(&stackH);

        if (mapping[vG] != -1 && mapping[vG] != vH) {
            free(mapping);
            freeListOfVertices(&stackG);
            freeListOfVertices(&stackH);
            return 0;
        }

        mapping[vG] = vH;

        ListOfVertices *adjG = adj(g, vG);
        ListOfVertices *adjH = adj(h, vH);

        if (adjG->len != adjH->len) {
            free(mapping);
            freeListOfVertices(&stackG);
            freeListOfVertices(&stackH);
            return 0;
        }

        for (unsigned int i = 0; i < adjG->len; i++) {
            appendListOfVertices(&stackG, adjG->sto[i]);
            appendListOfVertices(&stackH, adjH->sto[i]);
        }
    }

    freeListOfVertices(&stackG);
    freeListOfVertices(&stackH);

    emptyListOfVertices(phi);
    for (Vertex v = g->min; v <= g->max; v++) {
        appendListOfVertices(phi, mapping[v]);
    }

    free(mapping);
    return 1;
}

// sovle to find Root
Vertex findRoot(const Graph *g) {
    for (Vertex v = g->min; v <= g->max; v++) {
        if (indegree(g, v) == 0) {
            return v;
        }
    }
    return INT_MIN;
}

// An isomorphism between two rooted term DAGs



/*
 * // Sample test vectors:
 *
 * Graph g;
 * ListOfEdges es;
 * ListOfVertices vs;
 *
 * readGraph(&g, "0\n8\n0\n0\n0\n3 6 7 8\n2 3 1\n1 6\n1 1\n1 0\n3 5 0 2\n");
 * edges(&es, &g);
 * showListOfEdges(&es);
 * // 11
 * // 3 -> 6
 * // 3 -> 7
 * // 3 -> 8
 * // 4 -> 3
 * // 4 -> 1
 * // 5 -> 6
 * // 6 -> 1
 * // 7 -> 0
 * // 8 -> 5
 * // 8 -> 0
 * // 8 -> 2
 * printf(�g%d\n�h, indegree(&g, 0));
 * printf(�g%d\n�h, indegree(&g, 4));
 *  // 2
 *  // 0

 * vertices(&vs, &g);
 * showListOfVertices(&vs);
 * printf("\n");
 * //9 0 1 2 3 4 5 6 7 8
 * topSort(&vs, &g);
 * printf("%d\n", isaTopSort(&vs, &g));
 * // 1
 * showListOfVertices(&vs);
 * //9 4 3 7 8 5 6 0 1 2　この結果が違うが複数通りあるためOK?
 * freeGraph(&g);
 * freeListOfEdges(&es);
 * freeListOfVertices(&vs);
 * /




/*
 * // Sample test vectors:
 *
 * Graph g, h;
 * ListOfVertices phi;
 *
 * readGraph(&g, "1\n7\n0\n0\n1 2\n2 7 2\n1 1\n3 4 3 5\n0\n");
 * readGraph(&h, "1\n7\n0\n0\n0\n3 7 6 5\n1 1\n1 2\n2 3 2\n");
 *
 * isomorphism(&phi, &g, &h);     // 1
 * showListOfVertices(&phi);      // 7 1 2 6 7 5 4 3
 * isanIsomorphism(&phi, &g, &h); // 1
 *
 * freeGraph(&g);
 * freeGraph(&h);
 * freeListOfVertices(&phi);
 */

// Example uses

void die(const char *file, unsigned int line, const char *msg)
{
    printf("%s:%d: error: %s\n", file, line, msg);
    exit(-1);
}

int isEven(Vertex v)
{
    return v % 2 == 0;
}

int main(int argc, char *argv[])
{
//     Sample test vectors:
//    // homework 1
//    ListOfEdges es, fs;
//    readListOfEdges(&es, "2\n0 -> 1\n1 -> 2\n");
//    int a = isNullListOfEdges(&es);    // 0
//    copyListOfEdges(&fs, &es);
//    int b =isEqListOfEdges(&es, &fs); // 1
//    showListOfEdges(&fs);
//    // 2
//    // 0 -> 1
//    // 1 -> 2
//    freeListOfEdges(&es);
//    freeListOfEdges(&fs);
//    printf("%d, %d", a, b);

//    // homework2
//    // Sample test vectors:
//     Graph g;
//     ListOfEdges es;
//     ListOfVertices vs;
//     readGraph(&g, "0\n8\n0\n0\n0\n3 6 7 8\n2 3 1\n1 6\n1 1\n1 0\n3 5 0 2\n");
//     edges(&es, &g);
//     showListOfEdges(&es);
//     // 11
//     // 3 -> 6
//     // 3 -> 7
//     // 3 -> 8
//     // 4 -> 3
//     // 4 -> 1
//     // 5 -> 6
//     // 6 -> 1
//     // 7 -> 0
//     // 8 -> 5
//     // 8 -> 0
//     // 8 -> 2
//     printf("%d\n", indegree(&g, 0));
//     printf("%d\n", indegree(&g, 4));
//     // 2
//     // 0
//
//     vertices(&vs, &g);
//     showListOfVertices(&vs);
//     printf("\n");
//     //9 0 1 2 3 4 5 6 7 8
//     topSort(&vs, &g);
//     printf("%d\n", isaTopSort(&vs, &g));
//     // 1
//     showListOfVertices(&vs);
//     //9 4 3 7 8 5 6 0 1 2
//     freeGraph(&g);
//     freeListOfEdges(&es);
//     freeListOfVertices(&vs);
//
//
//
// Sample test vectors:
// homework3
//    Graph g, h;
//    ListOfVertices phi;
//
//    readGraph(&g, "1\n7\n0\n0\n1 2\n2 7 2\n1 1\n3 4 3 5\n0\n");
//    readGraph(&h, "1\n7\n0\n0\n0\n3 7 6 5\n1 1\n1 2\n2 3 2\n");
//    int a = isomorphism(&phi, &g, &h);     // 1
//    printf("%d\n", a);
//    showListOfVertices(&phi);      // 7 1 2 6 7 5 4 3
//    int b = isanIsomorphism(&phi, &g, &h); // 1
//    printf("\n%d\n", b);
//
//    freeGraph(&g);
//    freeGraph(&h);
//    freeListOfVertices(&phi);


//    const char *input = "0\n8\n0\n0\n0\n3 6 7 8\n3 3 1 1\n1 6\n1 1\n2 0 0\n3 5 0 2\n";
//    Graph g, h;
//    Edge e, f;
//    ListOfVertices vs, us;
//
//    readGraph(&g, input);
//    showGraph(&g);
//
//    copyGraph(&h, &g);
//    assert(isEqGraph(&g, &h));
//
//    freeGraph(&g);
//    freeGraph(&h);
//
//    readEdge(&e, "1 -> 2");
//    showEdge(&e);
//    printf("\n");
//    f = e;
//    assert(isEqEdge(&e, &f));
//
//    readListOfVertices(&us, "6 1 2 3 4 5 6");
//    showListOfVertices(&us); printf("\n");
//
//    filterListOfVertices(&vs, isEven, &us);
//    showListOfVertices(&vs); printf("\n"); removeAtHeadListOfVertices(&vs);
//    showListOfVertices(&vs); printf("\n"); removeAtHeadListOfVertices(&vs);
//    showListOfVertices(&vs); printf("\n"); removeAtHeadListOfVertices(&vs);
//    showListOfVertices(&vs); printf("\n"); removeAtHeadListOfVertices(&vs); // assertion should fail

    return 0;
}