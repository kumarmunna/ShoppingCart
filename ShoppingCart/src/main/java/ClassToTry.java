import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class ClassToTry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UniqueList ul = new UniqueList();
		bean b = new bean();
		b.setName("sant");
		b.setAdd("su");
		
	    ul.add(b);
	    bean b1 = new bean();
		b.setName("sant");
		b.setAdd("su");
		
	    ul.add(b1);
	    bean b2 = new bean();
		b.setName("sant");
		b.setAdd("su");
		
	    ul.add(b2);
	    
	    bean b3 = new bean();
		b.setName("santo");
		b.setAdd("sud");
		ul.add(b3);
		
	    System.out.println(ul);
	    Object[] content = ul.toObjectArray();
	    System.out.println(Arrays.toString(content));		
	}

}
class bean{
	String name;
	String add;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	
	
}

class UniqueList {
	  private HashSet masterSet = new HashSet();
	  private ArrayList innerList;
	  private Object[] returnable;

	  public UniqueList() {
	    innerList = new ArrayList();
	  }

	  public UniqueList(int size) {
	    innerList = new ArrayList(size);
	  }

	  public void add(Object thing) {
	    if (!masterSet.contains(thing)) {
	      masterSet.add(thing);
	      innerList.add(thing);
	    }
	  }
	  public List getList() {
	    return innerList;
	  }

	  public Object get(int index) {
	    return innerList.get(index);
	  }

	  public Object[] toObjectArray() {
	    int size = innerList.size();
	    returnable = new Object[size];
	    for (int i = 0; i < size; i++) {
	      returnable[i] = innerList.get(i);
	    }
	    return returnable;
	  }
	}
