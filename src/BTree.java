import java.util.List;
import java.util.*;
import java.util.Vector;

class Pair <Key extends Comparable<Key>, Value>{
    Key key;
    Value value;
    public Pair(Key k, Value v){
        this.key = k;
        this.value = v;
    }
    public Key getKey(){ return this.key;}
    public Value getValue(){ return this.value;}
}

class BTreeNode <Key extends Comparable<Key>, Value>{
    int pos = -1;
    int num_of_children = 0; 
    Pair <Key,Value>[] arr;
    BTreeNode <Key,Value>[] children_set;
    BTreeNode <Key,Value> parent;
    
    public BTreeNode(int b){
        arr = (Pair <Key,Value>[]) new Pair[b];
        children_set = (BTreeNode <Key,Value>[]) new BTreeNode[b+1];
        parent = null;
    }
    
    public Key keyAt(int i){
        return arr[i].getKey();
    }
    
    public Value valueAt(int i){
        return arr[i].getValue();
    }

    public int find_pos(Key k){ 
        int c2 = -1;                                                            
        for(int i =0; i<= pos;i++){
            int a = k.compareTo((Key)this.keyAt(i));
            if(a>0){continue;}
            else {
                c2 = i;
                break;
                }
        }
        if(c2==-1){c2=(pos + 1);}
        return c2;
    }
    
    public void addElement(Key k, Value v){
        Pair <Key,Value> temp = new Pair(k,v);
        int c2= this.find_pos(k);
        for(int i= this.pos; i>=c2;i--){
            this.arr[i+1] = this.arr[i];
        }
        (this.pos)++;
        this.arr[c2] = temp;
    }

    public void setParent(BTreeNode n){
        this.parent = n;
    }

    public void addChild(BTreeNode n){
        if(n == null){return;}
        n.setParent(this);
        int c2= this.find_pos((Key) n.keyAt(n.pos));
        children_set[c2] = n;
        num_of_children++; 
    }

    public BTreeNode RemoveElements(int a,int b){
        BTreeNode <Key,Value> temp = new BTreeNode(this.arr.length);
        int t = a-1;
        if(a>b){return null;}
        int count =0;
        for(int i=a;i<=b;i++){
            temp.addElement(this.keyAt(i),this.valueAt(i));
            count++;
            if(children_set[i] != null){
                temp.addChild(children_set[i]);
                children_set[i] = null;
                num_of_children--;
            }
        }
        if(children_set[pos + 1] != null){
                temp.addChild(children_set[pos + 1]);
                children_set[pos + 1] = null;
                num_of_children--;
            }
        pos = t-1;
        return temp;
    }

    public int lastChildAt(){
        int count=-1;
        for(int i=0;i<children_set.length;i++){
            if(children_set[i] != null){
                count = i;
            }
        }
        return count;
    }

    public int indexAsChild(){
        int val=-1;
        for(int i=0;i<(parent.children_set.length);i++){
            if(parent.children_set[i] != null){
                if(this.keyAt(0).compareTo(parent.children_set[i].keyAt(0)) == 0){
                    val=i;
                }
            }
        }
        return val;
    }

    public void rightShift(int n){
        for(int i=(this.lastChildAt() + 1); i>(n+1);i--){
            if(children_set[i-1] != null){
                children_set[i] = children_set[i-1];
            }
        }
    }
}

@SuppressWarnings("unchecked")
public class BTree <Key extends Comparable<Key>,Value>{
    BTreeNode <Key,Value> root;

    public BTree(int b) {
//        if ( b%2 == 0){
            root = new BTreeNode(b);    
//        }
    }

    public BTree <Key,Value> subtree(int n){
        BTree <Key,Value> temp = new BTree(root.arr.length);
        temp.root = root.children_set[n];
        return temp;
    }

    public BTreeNode fixOverflow(BTreeNode n){
        BTreeNode <Key,Value> new_root = new BTreeNode(n.arr.length);
        Pair <Key,Value> temp1 ;
        int c = (n.pos + 1)/2;
        temp1 = n.arr[c];
        
        if(n.parent == null){
            n.parent = new BTreeNode(n.arr.length);
            n.parent.addElement(temp1.getKey(),temp1.getValue());
            BTreeNode <Key,Value> temp2 = new BTreeNode(n.arr.length);
            temp2 = n.RemoveElements(c+1,n.pos);
            n.parent.addChild(temp2);
            n.parent.addChild(n);
            new_root = n.parent;
        }
        else{
            n.parent.addElement(temp1.getKey(),temp1.getValue());
            n.parent.rightShift(n.indexAsChild());
            BTreeNode <Key,Value> temp2 = new BTreeNode(n.arr.length);
            temp2 = n.RemoveElements(c+1,n.pos);
            n.parent.addChild(temp2);
            new_root = root;
            if(n.parent.arr.length == (n.parent.pos + 1)) { new_root = this.fixOverflow(n.parent);}
        }
        return new_root;
    }

    public BTreeNode insert_item(BTreeNode n, Key key, Value val) {
        BTreeNode <Key,Value> new_root = new BTreeNode(n.arr.length);
        if(n.num_of_children != 0){
            int c1= n.find_pos(key);
            new_root = insert_item(n.children_set[c1],key,val);
        }
        else { 
            n.addElement(key,val);
            new_root = root;
            if(n.arr.length == (n.pos + 1)) 
                new_root = this.fixOverflow(n);
        }
        return new_root;
    }
    
    public void insert(Key key, Value val) {
        Pair <Key,Value> temp = new Pair(key,val);
        if(root.pos == -1){
                (root.pos)+= 1;
                root.arr[root.pos] = temp;
            }
        else
            root = insert_item(root, key, val);
    }
    
    @Override
    public String toString(){ 
        return print(root);
    }

    public String print(BTreeNode n){ 
        String temp = "[";
        for(int i=0;i<=n.pos;i++){
            if(i !=0)
                temp = temp + ",";
            if(n.children_set[i] != null)
                temp = temp + (print(n.children_set[i])) + ",";
            temp = temp + (Key)n.keyAt(i).toString();
        }
        if(n.children_set[n.pos + 1] != null)
            temp = temp + "," + print(n.children_set[n.pos + 1]);
        temp = temp + "]";
        return temp;
    }
 
    public static void main(String[] args) {
        BTree <Integer,String> tree = new BTree(4);
        tree.insert(3,"3");
        tree.insert(7,"7");
        tree.insert(9,"9");
        tree.insert(23,"23");
        tree.insert(45,"45");
        tree.insert(1,"1");
        tree.insert(5,"5");
        tree.insert(14,"14");
        tree.insert(5,"55");
        tree.insert(24,"24");
        tree.insert(13,"13");
        tree.insert(11,"11");
        tree.insert(8,"8");
        tree.insert(19,"19");
        tree.insert(4,"4");
        tree.insert(31,"31");
        tree.insert(35,"35");
        tree.insert(56,"56");
        System.out.println( tree.toString());
        tree.delete(45, "45");
        System.out.println( tree.toString());
        tree.delete(11, "11");
        System.out.println( tree.toString());
//        test_alpha.delete(31, "31");
//        System.out.println( test_alpha.toString());
    }
    public void delete(Key key, Value val){
        BTreeNode x = searchd(key);
        delete_item(x, key);
    }
    public BTreeNode searchd_in(BTreeNode n, Key key){    
        BTreeNode hereitis =null;
        BTreeNode temp;
        for(int i=0;i<=n.pos;i++){
                int a = key.compareTo((Key)n.keyAt(i));
                if(a<0){break;}
                if(a==0)
                    hereitis = n;
            }
        
        for(int i=0;i<n.children_set.length;i++){
            if(n.children_set[i] != null)
            {
                temp = searchd_in(n.children_set[i],key);
                if(temp != null)
                {
                    hereitis = temp;
                }
            }                
        }
        return hereitis;
    }
    
    public BTreeNode searchd(Key key){
        return searchd_in(root,key);
    }
//   
        public void delete_item(BTreeNode x, Key k) {
            if(x.num_of_children != 0) {                
                BTreeNode y = x.children_set[x.find_pos(k)];
                BTreeNode z = x.children_set[x.find_pos(k)+1];
                if(y.pos+1 > 1) {
                    Key k1 = (Key) y.keyAt(y.pos);
                    Value v1 = (Value) y.keyAt(y.pos);
                    x.arr[x.find_pos(k)] = y.arr[y.pos];
                    for(int u=y.find_pos(k1);u<=y.pos;u++){
                        y.arr[u] = y.arr[u+1];
                    }
                    y.pos=y.pos-1;
                }
                else if(z.pos+1 > 1) {
                    Key k1 = (Key) z.keyAt(0);
                    x.arr[x.find_pos(k)] = z.arr[0];
                    for(int u=z.find_pos(k1);u<=z.pos;u++){
                        z.arr[u] = z.arr[u+1];
                    }
                    z.pos=z.pos-1;
                }
                else {
                    for(int u=0;u<=z.pos;u++){
                        y.arr[y.pos+u+1] = z.arr[u];
                        y.pos+=1;
                    }
                    for(int w=x.find_pos(k);w<x.pos;w++){
                        x.arr[w] = x.arr[w+1];
                        x.children_set[w+1] = x.children_set[w+2];
                    }
                    x.pos-=1;
                    x.num_of_children-=1;
                }
            }
            else {
                BTreeNode w = x.parent;
                BTreeNode y = null;
                BTreeNode z = null;
                int node_value = 0;
                for(int u = 0; u < w.num_of_children;u++){
                    if(w.children_set[u] == x)
                    {
                        node_value = u;
                    }
                }
                if(node_value!=0){y = w.children_set[node_value-1];}
                else if(node_value != w.pos+1){z = x.children_set[x.find_pos(k)+1];}
                System.out.println(y +" "+z);
                if(x.pos > 1)
                {
                    for(int u = x.find_pos(k); u<x.pos;u++)
                        x.arr[u] = x.arr[u+1];
                    x.pos-=1;
                }
                else if(w.pos <= 1 && y.pos <= 1 && z.pos <= 1){
//                    System.out.println("this is the case I want");
                }
            }
        }
}