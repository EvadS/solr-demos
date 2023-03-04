package com.se.sample;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(System.getProperty("java.vm.name"));
        System.out.println(System.getProperty("java.vm.info"));

        System.out.println(System.getProperty("os.arch"));

        System.out.println( "Hello World!" );
    }
}
