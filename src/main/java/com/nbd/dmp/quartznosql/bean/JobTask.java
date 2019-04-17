package com.nbd.dmp.quartznosql.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Timing task configuration description
 *
 * @author duchen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTask
{
    //Task name
    private String             key;
    //Task display name
    private String             title;
    //Task group
    private String             group;
    //Task description
    private String             description;
    //Task class
    private Class              taskClass;
    //Task execution method list
    private List<Method>       methodList;
    //Task field set method map
    private Map<String,Method> fieldSetMethodMap;
    //Is concurrent?
    //When is true,stateful tasks can not be executed concurrently.
    //When is false,stateless tasks can execute concurrently.
    private boolean            concurrent;
    //If task class is from jar package,store it's url
    private URL                jarClassUrl;

}