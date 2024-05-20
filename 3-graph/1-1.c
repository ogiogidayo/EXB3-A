#include <stdio.h>
#include <malloc/_malloc.h>
// The basic type of edges

typedef int Vertex;
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

    for (int i = 0; i < es->len; i++) {
        if (!isEqEdge(&es->sto[i], &fs->sto[i])) {
            return 0;
        }
    }

    return 1;
};

void copyListOfEdges(ListOfEdges *es, const ListOfEdges *fs){   // copy fs to es
    es->len = fs->len;
    es->sto = malloc(es->len * sizeof(*es->sto));
    for (int i = 0; i < es->len; i++) {
        es->sto[i] = fs->sto[i];
    }
};

int isNullListOfEdges(const ListOfEdges *es){ // test if *es == []
    return es->len == 0;
};

const char *readListOfEdges(ListOfEdges *es, const char *str) { // read es from str
    int offset;

    sscanf(str, " %d %n", &es->len, &offset); str += offset;
    es->sto = malloc(es->len * sizeof(*es->sto));
    for (int i = 0; i < es->len; i ++) {
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

int main(void) {
    ListOfEdges es, fs;
    readListOfEdges(&es, "2\n0 -> 1\n1 -> 2\n");
    int a = isNullListOfEdges(&es);
    printf("%d\n", a);  // 0
    copyListOfEdges(&fs, &es);
    int b = isEqListOfEdges(&es, &fs); // 1
    printf("%d\n", b);
    showListOfEdges(&fs);
    // 2
    // 0 -> 1
    // 1 -> 2
    freeListOfEdges(&es);
    freeListOfEdges(&fs);

    return 0;
}