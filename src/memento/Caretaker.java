package memento;

import java.util.ArrayDeque;

public class Caretaker {


	ArrayDeque<Memento> mementoList = new ArrayDeque<>();
	
	public void add(Memento memento) {
		if(mementoList.size()==1){
			mementoList.pop();
			mementoList.push(memento);
		}else {
			mementoList.push(memento);
		}
	}

	public int mementoListSize(){
		return mementoList.size();
	}

	public Memento getLast() {
		Memento memento = mementoList.peek();
		mementoList.pop();
		return memento;
	}


}
