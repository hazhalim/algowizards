package com.algowizards.finalpricetracker;

import java.util.*;

/**
 *
 * @author AlgoWizards
 * 
 * Class Description: This class contains subclasses that represent data structures used in this program
 * Current Subclasses: List1D (1-dimensional ArrayLists), List2D (2-dimensional ArrayLists), and Mapping (HashMap)
 * 
 */

public class DataStructure<T>
{

    // Subclass for 1D lists
    public static class List1D<T> extends DataStructure<T> // 1D list of any type
    {

        // Instance variables
        private List<T> list1D;
        private int list1DSize;

        // Constructor
        public List1D() // Default constructor
        {

            throw new IllegalArgumentException("No arguments passed into constructor");

        }

        public List1D(List<T> list1D)
        {

            this.list1D = list1D;
            this.list1DSize = list1D.size();

        }

        // Getter and setter methods
        // Getter methods
        List<T> getList1D()
        {

            return this.list1D;

        }

        int getList1DSize()
        {

            return this.list1DSize;

        }

        T getList1DValue(int i)
        {

            return this.list1D.get(i);

        }

        // Setter methods
        void setList1D(List<T> list1D)
        {

            this.list1D = list1D;

        }

        void setList1DSize(int list1DSize)
        {

            if (list1DSize >= 0)
            {

                this.list1DSize = list1DSize;

            }

        }

        void setList1DValue(int i, T list1DValue)
        {

            if (list1DValue != null)
            {


                this.list1D.set(i, list1DValue);


            } else {

                throw new IllegalArgumentException("Value to be placed cannot be null");

            }

        }

        // Other methods

    }

    // Subclass for 2D lists
    public static class List2D<T> extends DataStructure<T> // 2D list of any type
    {

        // Instance variables
        private List<List<T>> list2D;
        private int rowSize;
        private int columnSize;

        // Constructor
        public List2D() // Default constructor
        {

            throw new IllegalArgumentException("No arguments passed into constructor");

        }

        public List2D(List<List<T>> list2D)
        {

            this.list2D = list2D;
            this.rowSize = this.list2D.size();
            this.columnSize = (this.rowSize > 0) ? (this.list2D.get(0).size()) : 0; // Assuming number of columns is the same on all rows

        }

        // Getter and setter methods
        // Getter methods
        int getRowSize()
        {

            return this.rowSize;

        }

        int getColumnSize()
        {

            return this.columnSize;

        }

        List<List<T>> getList2D()
        {

            return this.list2D;

        }

        List<T> getRow(int i) {

            return this.list2D.get(i);

        }

        List<T> getColumn(int j)
        {

            List<T> column = new ArrayList<>();

            if ((j >= 0) && (j < getColumnSize()))
            {

                for (List<T> row : list2D)
                {

                    if (j < row.size())
                    {

                        column.add(row.get(j));

                    }

                }

            } else {

                throw new IllegalArgumentException("Method getColumn(" + j + ") failed: column index " + j + "is out of bounds");

            }

            return column;

        }

        T getValue(int i, int j)
        {

            return this.list2D.get(i).get(j);

        }

        // Setter methods
        void setRowSize(int rowSize)
        {

            if (rowSize >= 0)
            {

                this.rowSize = rowSize;

            } else {

                throw new IllegalArgumentException("Row size must be at least 0");

            }

        }

        void setColumnSize(int columnSize)
        {

            if (columnSize >= 0)
            {

                this.columnSize = columnSize;

            } else {

                throw new IllegalArgumentException("Column size must be at least 0");

            }

        }

        void setList2D(List<List<T>> list2D)
        {

            if (list2D != null)
            {

                this.list2D = list2D;

            } else {

                throw new IllegalArgumentException("2D list provided must not be null");

            }

        }

        void setRow(int i, List<T> row)
        {

            if (row != null)
            {

                this.list2D.set(i, row);

            }

        }

        void setColumn(int j, List<T> column)
        {

            if ((column != null) && (j >= 0))
            {

                int limitingSize = Math.min(this.rowSize, column.size());

                for (int i = 0; i < limitingSize; i++)
                {

                    this.list2D.get(i).set(j, column.get(i)); // Set the j-th column of the i-th row of list2D to the i-th row of the 1D list column

                }

            } else {

                throw new IllegalArgumentException("The list provided is null or the column index provided must be at least 0");

            }

        }

        void setValue(int i, int j, T value)
        {

            if (value != null)
            {

                this.list2D.get(i).set(j, value);

            }

        }

        // Other methods

    }

    // Subclass for mappings (maps)
    public static class Mapping<K, V> extends DataStructure<V>
    {

        // Instance variables
        private Map<K, V> mapping;

        private List<K> keys;
        private List<V> values;

        // Constructor
        public Mapping() // Default constructor
        {

            this.mapping = new HashMap<>();

        }

        public Mapping(Map<K, V> mapping)
        {

            this.mapping = new HashMap<>(mapping);

            this.keys = new ArrayList<>(mapping.keySet()); // Converts Set to ArrayList
            this.values = new ArrayList<>(mapping.values()); // Converts Collection to ArrayList

        }

        // Getter and setter methods
        // Getter methods
        V getValueByKey(K key) // Returns the value mapped to a given key
        {

            return this.mapping.get(key);

        }

        Map.Entry<K, V> getEntryByKey(Map<K, V> mapping, K key) // Returns an entry (key, value) given a key
        {

            for (Map.Entry<K, V> entry : mapping.entrySet())
            {

                if (entry.getKey().equals(key))
                {

                    return entry;

                }

            }

            return null; // Unable to get an entry because the key was not found (does not exist)

        }

        boolean containsKey(K key) // Determines whether this key exists in the map or not
        {

            return this.mapping.containsKey(key);

        }

        List<K> getKeys()
        {

            return this.keys;

        }

        List<V> getValues()
        {

            return this.values;

        }

        Map<K, V> getMapping()
        {

            return this.mapping;

        }

        // Setter methods
        void setMapping(Map<K, V> mapping)
        {

            this.mapping = new HashMap<>(mapping);

            this.keys = new ArrayList<>(this.mapping.keySet());
            this.values = new ArrayList<>(this.mapping.values());

        }

        void setEntry(K key, V value)
        {

            if ((key != null) && (this.mapping.containsKey(key)))
            {

                this.mapping.put(key, value);

                // Regenerate the list for keys and values
                setKeys();
                setValues();

            } else {

                throw new IllegalArgumentException("The key provided must not be null or the key was not found in the map");

            }

        }

        void setKeys()
        {

            this.keys = new ArrayList<>(this.mapping.keySet());

        }

        void setValues()
        {

            this.values = new ArrayList<>(this.mapping.values());

        }

        // Other methods
        void removeEntry(K keyToBeRemoved)
        {

            Map<K, V> modifiedMap = getMapping();

            modifiedMap.remove(keyToBeRemoved);

            setMapping(modifiedMap);

        }

        void addEntry(K key, V value)
        {

            Map<K, V> modifiedMap = getMapping();

            modifiedMap.put(key, value);

            setMapping(modifiedMap);
            setKeys();
            setValues();

        }

    }
    
}