package com.don.demo.basic.reflect;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 作者: Throwable
 链接: https://www.throwx.cn/2019/12/25/java-introspector-usage/
 来源: Throwable
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author Carl Don
 * @Date 2023/3/26  12:25
 * @Version 1.0
 */
public class IntrospectorTest {


        public static void main(String[] args) throws Exception {
            BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (!"class".equals(propertyDescriptor.getName())) {
                    System.out.println(propertyDescriptor.getName());
                    System.out.println(propertyDescriptor.getWriteMethod().getName());
                    System.out.println(propertyDescriptor.getReadMethod().getName());
                    System.out.println("=======================");
                }
            }
        }

        public static class Person {

            private Long id;
            private String name;
            private Integer age;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getAge() {
                return age;
            }

            public void setAge(Integer age) {
                this.age = age;
            }
        }




}
