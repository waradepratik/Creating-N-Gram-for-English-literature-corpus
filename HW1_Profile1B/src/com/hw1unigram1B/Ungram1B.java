package com.hw1unigram1B;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.hw1unigram1B.Ungram1B;
import com.hw1unigram1B.Unigram1BMapper;
import com.hw1unigram1B.Unigram1BReducer;
import com.wholefileinputformat.WholeFileInputFormat;


public class Ungram1B {
	public static void main(String[] args) throws Exception {
		
		if (args.length != 2) {
		      System.out.printf("Usage: <jar file> <input dir> <output dir>\n");
		      System.exit(-1);
		    }
		
		Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(Ungram1B.class);



        job.setMapperClass(Unigram1BMapper.class);
        //job.setCombinerClass(Unigram1AReducer.class);
        job.setReducerClass(Unigram1BReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        
        job.setNumReduceTasks(40);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
