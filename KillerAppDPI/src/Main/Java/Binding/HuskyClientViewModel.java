package Binding;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mix.model.Husky;

import java.util.*;

/**
 * Created by Gebruiker on 12-3-2019.
 */
public class HuskyClientViewModel implements ViewModel {

    //region fields
    private ObservableList huskyList = new ObservableList() {
        @Override
        public void addListener(ListChangeListener listener) {

        }

        @Override
        public void removeListener(ListChangeListener listener) {

        }

        @Override
        public boolean addAll(Object[] elements) {
            return false;
        }

        @Override
        public boolean setAll(Object[] elements) {
            return false;
        }

        @Override
        public boolean setAll(Collection col) {
            return false;
        }

        @Override
        public boolean removeAll(Object[] elements) {
            return false;
        }

        @Override
        public boolean retainAll(Object[] elements) {
            return false;
        }

        @Override
        public void remove(int from, int to) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Object get(int index) {
            return null;
        }

        @Override
        public Object set(int index, Object element) {
            return null;
        }

        @Override
        public void add(int index, Object element) {

        }

        @Override
        public Object remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator listIterator() {
            return null;
        }

        @Override
        public ListIterator listIterator(int index) {
            return null;
        }

        @Override
        public List subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public void addListener(InvalidationListener listener) {

        }

        @Override
        public void removeListener(InvalidationListener listener) {

        }
    };
//    private StringProperty helloMessage = new SimpleStringProperty("Hello World");

    //endregion


    //region Properties
    public ObservableList<Husky> huskyList(){
        return huskyList;
    }

//    public StringProperty helloMessageProperty(){
//        return helloMessage;
//    }
//    public String getHelloMessage(){
//        return helloMessage.get();
//    }
//    public void setHelloMessage(String message){
//        helloMessage.set(message);
//    }

    //endregion
}
