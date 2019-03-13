package mix.model;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Created by Gebruiker on 11-3-2019.
 */
public class Owner {

    private String firstName;
    private String lastName;
    private Date birthdate;

    private ObservableList<Husky> huskyList;

    public Owner(String firstname, String lastName, Date birthDate)
    {
        this.firstName = firstname;
        this.lastName = lastName;
        this.birthdate = birthDate;

        //this normally comes from a database
        huskyList = new ObservableList<Husky>() {
            @Override
            public void addListener(ListChangeListener<? super Husky> listener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super Husky> listener) {

            }

            @Override
            public boolean addAll(Husky... elements) {
                return false;
            }

            @Override
            public boolean setAll(Husky... elements) {
                return false;
            }

            @Override
            public boolean setAll(Collection<? extends Husky> col) {
                return false;
            }

            @Override
            public boolean removeAll(Husky... elements) {
                return false;
            }

            @Override
            public boolean retainAll(Husky... elements) {
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
            public Iterator<Husky> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Husky husky) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Husky> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Husky> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Husky get(int index) {
                return null;
            }

            @Override
            public Husky set(int index, Husky element) {
                return null;
            }

            @Override
            public void add(int index, Husky element) {

            }

            @Override
            public Husky remove(int index) {
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
            public ListIterator<Husky> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Husky> listIterator(int index) {
                return null;
            }

            @Override
            public List<Husky> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        };
        huskyList.add(new Husky(55,20, 120, "Husko", new Date(2017,8,23)));
        huskyList.add(new Husky(51,18, 113, "Husshi", new Date(2018,2,24)));
        huskyList.add(new Husky(58,21, 125, "Sushi", new Date(2017,6,20)));
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Husky> getHuskyList() {
        return huskyList;
    }
}
