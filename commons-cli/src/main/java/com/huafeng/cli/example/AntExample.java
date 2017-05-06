package com.huafeng.cli.example;


import org.apache.commons.cli.*;

/**
 * Created by i067561 on 06/05/2017.
 */
public class AntExample {

    private static Options init (){
        Option help         = new Option( "help", "print this message" );
        Option projecthelp  = new Option( "projecthelp", "print project help information" );
        Option version      = new Option( "version", "print the version information and exit" );
        Option quiet        = new Option( "quiet", "be extra quiet" );
        Option verbose      = new Option( "verbose", "be extra verbose" );
        Option debug        = new Option( "debug", "print debugging information" );
        Option emacs        = new Option( "emacs",
                "produce logging information without adornments" );


        Option logfile  = Option.builder( "logfile" )
                .hasArg()
                .desc (  "use given file for log" )
                .argName( "file" )
                .build();

        Option logger   = Option.builder( "logger" )
                .hasArg()
                .desc ("the class which it to perform "
                        + "logging" )
                .argName( "classname" )
                .build();

        Option property  = Option.builder( "D" )
                .numberOfArgs(2)
                .valueSeparator()
                .desc( "use value for given property" )
                .argName( "property=value" )
                .build();

        Options options = new Options();

        options.addOption( help );
        options.addOption( projecthelp );
        options.addOption( version );
        options.addOption( quiet );
        options.addOption( verbose );
        options.addOption( debug );
        options.addOption( emacs );
        options.addOption( logfile );
        options.addOption( logger );

        options.addOption( property );
        return options;
    }

    public static void main (String args[]){
        // create the parser
        CommandLineParser parser = new DefaultParser();
        Options opitons= init();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( init(), args );

            // has the buildfile argument been passed?
            if( line.hasOption( "buildfile" ) ) {
                // initialise the member variable
                String buildfile = line.getOptionValue( "buildfile" );
                System.out.println(buildfile);
            }
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );

         }
        printHelp(opitons);
    }


    private static void printHelp (Options options){
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "ant", options , true);
    }


}
