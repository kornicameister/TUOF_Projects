package org.kornicameister.iad.core.util.data.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.kornicameister.iad.core.annotation.DataProperty;
import org.kornicameister.iad.core.util.data.DataFlow;
import org.kornicameister.iad.core.util.data.IAData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public class CSVDataFlow<T extends IAData> extends DataFlow<T> {

    public CSVDataFlow(final String filePath) {
        super(filePath);
    }

    private String[] generateColumnMapping(Class<T> beanClass) {
        List<String> columns = new ArrayList<>();
        for (Field field : beanClass.getDeclaredFields()) {
            DataProperty dataProperty;
            if ((dataProperty = field.getAnnotation(DataProperty.class)) != null) {
                String columnName = dataProperty.name();
                Integer columnIndex = dataProperty.index();
                columns.add(columnIndex, columnName);
            }
        }
        return (String[]) columns.toArray();
    }

    @Override
    public List<T> read(Class<T> beanClass) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader(new FileReader(this.filePath));

        ColumnPositionMappingStrategy<T> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(beanClass);
        mappingStrategy.setColumnMapping(this.generateColumnMapping(beanClass));


        return new CsvToBean<T>().parse(mappingStrategy, csvReader);
    }

    @Override
    public void write() {
    }
}
