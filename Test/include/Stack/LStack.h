//
// Created by Christopher on 2023/10/20.
//

#ifndef LAB01_LSTACK_H
#define LAB01_LSTACK_H

#include "Stack.h"
#include "Link.h"
#include "assert.h"

#define DEFAULT_SIZE 0

// Linked stack implementation
template<typename E>
class LStack : public Stack<E> {
private:
    // Pointer to first element
    Link <E> *top;
    // Number of elements
    int size;
public:
    // Constructor
    LStack(int sz = DEFAULT_SIZE) {
        top = nullptr;
        size = sz;
    }

    // Destructor
    ~LStack() { clear(); }

    // Reinitialize
    void clear() {
        // Delete link nodes
        while (top != nullptr) {
            Link <E> *temp = top;
            top = top->next;
            delete temp;
        }
        size = 0;
    }

    // Put "it" on stack
    void push(const E &it) {
        top = new Link<E>(it, top);
        size++;
    }

    // Remove "it" from stack
    E pop() {
        if (top == nullptr) {
            assert("栈已空");
        }

        E it = top->element;
        Link <E> *ltemp = top->next;
        delete top;
        top = ltemp;
        size--;
        return it;
    }

    // Return top value
    const E &topValue() const {
        if (top == nullptr) {
            assert("栈已空");
        }

        return top->element;
    }

    // Return length
    int length() const { return size; }
};

#endif //LAB01_LSTACK_H
