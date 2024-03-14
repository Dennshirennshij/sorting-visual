package github.dennshirennshij.nodedev74.sorting_visual.sorting;

import java.util.ArrayList;

public class VisualList extends ArrayList<Integer> {

    private boolean internalAdd (Integer element) {
        return super.add(element);
    }

    private Integer internalSet (int index, Integer element) {
        return super.set(index, element);
    }

    private Integer internalGet (int index) {
        return super.get(index);
    }

    @Override
    public Integer set(int index, Integer element) {
        System.out.println("Set " + element + " at " + index);
        return internalSet(index, element);
    }

    @Override
    public Integer get(int index) {
        System.out.println("Get " + internalGet(index) + " at " + index);
        return internalGet(index);
    }

    public void trade (int i1, int i2) {
        System.out.println("Trade " + i1 + " with " + i2);
        Integer i1_val = super.get(i1);
        super.set(i1, super.get(i2));
        super.set(i2, i1_val);
    }

    public VisualList (int... elements) {
        super();
        for (int element : elements) {
            internalAdd(element);
        }
    }

    public int[] toIntArray () {
        int[] array = new int[this.size()];
        for (int i = 0; i < this.size(); i++) {
            array[i] = this.internalGet(i);
        }
        return array;
    }

}
