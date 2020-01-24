package Skiped_List;

public class ExpressLinkedList {
    int size;
    ExpressNode head;

    public void setHead(ExpressNode head) {
        this.head = head;
    }

    public ExpressLinkedList() {
        size = 0;
        head = null;
    }

    public NormalNode GetLastNormal() {
        ExpressNode ex = head;
        while (ex.next != null)
            ex = ex.next;
        NormalNode nor = ex.current;
        while (nor.next != null)
            nor = nor.next;
        return nor;
    }

    public void Insert(int data) {

        ExpressNode ex = head;
        int sqrtSize = (int) (Math.sqrt(size));

        if (size == 0) {
            NormalNode nor = new NormalNode(data);
            ExpressNode newExpress = new ExpressNode(data, nor);
            setHead(newExpress);
            size++;
            return;
        }

        if (ex.next == null) {
            NormalNode normalNode = new NormalNode(data);
            ExpressNode expressNode = new ExpressNode(data, normalNode);
            size++;
            if (data >= ex.data) {
                ex.current.next = normalNode;
                ex.next = expressNode;
            } else {
                normalNode.next = ex.current;
                expressNode.next = ex;
                head = expressNode;
            }
            return;
        }

        int find = 0;
        int sw = 0;
        NormalNode newNormal = new NormalNode(data);
        if (data < ex.data) {
            ExpressNode expressNode = new ExpressNode(data, newNormal);
            newNormal.next = ex.current;
            expressNode.next = ex;
            ex = head = expressNode;
            size++;
            if (size == 3)
                return;
            sw = 1;
        } else {
            main:
            while (ex != null) {
                if ((ex.next == null && data >= ex.data) || (data >= ex.data && data < ex.next.data)) {
                    NormalNode nor = ex.current;
                    for (int k = 0; k < sqrtSize && nor.next != null && data > nor.next.data; k++) {
                        nor = nor.next;
                    }
                    newNormal.next = nor.next;
                    nor.next = newNormal;
                    size++;
                    sqrtSize = (int) Math.sqrt(size);
                    break main;
                }
                find++;
                ex = ex.next;
            }
        }

        if (sqrtSize == (int) Math.sqrt(size - 1)) {
            if (sw == 1) {
                while (ex.next != null) {
                    if (ex.next.current.next == null) {
                        ex.next = null;
                        return;
                    }
                    ex.next.data = ex.next.current.next.data;
                    ex.next.current = ex.next.current.next;
                    ex = ex.next;
                }
            } else {
                while (ex.next != null) {
                    NormalNode prev = ex.current;
                    while (prev.next != ex.next.current)
                        prev = prev.next;

                    ex.next.current = prev;
                    ex.next.data = prev.data;
                    ex = ex.next;
                }
            }

            if ((size % sqrtSize == 1 || sqrtSize == 1) && sw != 1) {
                NormalNode lastNormal = GetLastNormal();
                ex.next = new ExpressNode(lastNormal.data, lastNormal);
            }
        } else {
            ex = head.next;
            int i = 0;
            while (ex.next != null) {
                if (i < find) {
                    for (int j = 0; j < i + 1; j++) {
                        ex.current = ex.current.next;
                        ex.data = ex.current.data;
                    }
                } else {
                    for (int j = 0; j < i; j++) {
                        ex.current = ex.current.next;
                        ex.data = ex.current.data;
                    }
                }
                if (((size - 1) / sqrtSize) - 1 == i) {
                    ex.next = null;
                    return;
                }
                i++;
                ex = ex.next;
            }
        }
    }


    public NormalNode Erase(int data){

        ExpressNode ex = head;
        int sqrtSize = (int)(Math.sqrt(size));
        NormalNode result = null;

        if(ex.current.data == data){
            result = ex.current;
            ex.data = ex.current.next.data;
            ex.current = ex.current.next;
        }
        else {
            main : while (ex != null){
                if((ex.next == null && data >= ex.data) || (data >= ex.data && data < ex.next.data)){
                    NormalNode nor = ex.current;
                    if (data == ex.current.data){
                        ExpressNode h = head;
                        while (h.next != ex){
                            h = h.next;
                        }
                        NormalNode c = h.current;
                        while (c.next.data != ex.current.data){
                            c = c.next;
                        }
                        result = c.next;
                        c.next = c.next.next;
                        h.next = ex.next;
                        size--;
                        sqrtSize = (int)Math.sqrt(size);
                        break main;
                    }
                    else {
                        for (int k = 0; k < sqrtSize && nor != null && data != nor.next.data; k++){
                            nor = nor.next;
                        }

                        if(ex.next != null && nor.data == ex.next.current.data)
                            return null;

                        result = nor.next;
                        nor.next = nor.next.next;
                        size--;
                        sqrtSize = (int)Math.sqrt(size);
                        break main;
                    }
                }
                ex = ex.next;
            }
        }

        if(sqrtSize == (int)Math.sqrt(size + 1)){

            while (ex.next != null){
                if(ex.next.current.next == null){
                    ex.next = null;
                    return result;
                }
                ex.next.data = ex.next.current.next.data;
                ex.next.current = ex.next.current.next;
                ex = ex.next;
            }

            if(size % sqrtSize == 0 || sqrtSize == 1){
                ex.next = null;
            }

        }

        else {
            ex = head;
            int i = 0;

            while (size / sqrtSize - 1 != i){
                NormalNode current = ex.current;
                for(int j = 0; j < sqrtSize; j++){
                    current = current.next;
                }
                if(ex.next != null) {
                    ex.next.data = current.data;
                    ex.next.current = current;
                }
                else {
                    ex.next = new ExpressNode(current.data, current);
                }
                i++;
                ex = ex.next;
            }

        }

        return result;
    }

    public NormalNode Find(int x){
        ExpressNode ex = head;

        while (ex.next != null){
            if (ex.data <= x && (ex.next).data >= x){
                NormalNode nor = ex.current;
                while (nor != ex.next.current.next){
                    if(nor.data == x)
                        return nor;
                    nor = nor.next;
                }
            }
            ex = ex.next;
        }

        return null;
    }

    public void PrintNormalList(){
        NormalNode nor = head.current;
        while(nor != null){
            System.out.print(nor.data + " ");
            nor = nor.next;
        }
    }

    public void PrintExpressList(){
        ExpressNode ex = head;
        while(ex != null){
            System.out.print(ex.data + " ");
            ex = ex.next;
        }
    }
}
