package at.technikum.mvvm.repository;

import at.technikum.mvvm.event.Event;
import at.technikum.mvvm.event.EventAggregator;
import at.technikum.mvvm.model.Word;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class WordRepository {


    private final EventAggregator eventAggregator;

    public WordRepository(
            EventAggregator eventAggregator
    ) {
        this.eventAggregator = eventAggregator;
    }


    public void save(Word word) {

        eventAggregator.publish(Event.NEW_WORD);
    }

    public List<Word> findAll() {
        return new List<Word>() {
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
            public Iterator<Word> iterator() {
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
            public boolean add(Word word) {
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
            public boolean addAll(Collection<? extends Word> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Word> c) {
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
            public Word get(int index) {
                return null;
            }

            @Override
            public Word set(int index, Word element) {
                return null;
            }

            @Override
            public void add(int index, Word element) {

            }

            @Override
            public Word remove(int index) {
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
            public ListIterator<Word> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Word> listIterator(int index) {
                return null;
            }

            @Override
            public List<Word> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }
}
