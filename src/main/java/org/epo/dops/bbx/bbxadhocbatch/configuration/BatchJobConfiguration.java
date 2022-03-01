package org.epo.dops.bbx.bbxadhocbatch.configuration;

import lombok.RequiredArgsConstructor;
import org.epo.dops.bbx.bbxadhocbatch.model.PublicationInput;
import org.epo.dops.pbuloading.save.function.PublicationDelete;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchJobConfiguration {

    private final PublicationDelete publicationDelete;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(@Qualifier("downloadFlatFileFromRemoteServer") Step step1,
                   @Qualifier("readProcessAndWriteResults") Step step2) {

        return jobBuilderFactory.get("myJob")
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    protected Step downloadFlatFileFromRemoteServer(Tasklet downloadFileFromFtp) {
        return stepBuilderFactory.get("step1")
                .tasklet(downloadFileFromFtp)
                .build();
    }

    @Bean
    protected Tasklet downloadFileFromFtp(){

        //download the FlatFile from the FTP server.
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution,
                                        ChunkContext chunkContext) throws Exception {
                return null;
            }
        };
    }

    @Bean
    protected Step readProcessAndWriteResults(ItemReader<PublicationInput> reader,
                                              ItemProcessor<PublicationInput, PublicationInput> processor,
                                              ItemWriter<PublicationInput> writer) {

        return stepBuilderFactory.get("step2")
                .<PublicationInput, PublicationInput>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    protected ItemReader<PublicationInput> reader() {


        return new ItemReader<PublicationInput>() {
            @Override
            public PublicationInput read() throws Exception, UnexpectedInputException,
                    ParseException, NonTransientResourceException {
                return null;
            }
        };
    }

    @Bean
    protected ItemProcessor<PublicationInput, PublicationInput> processor() {

        return new ItemProcessor<>() {
            @Override
            public PublicationInput process(PublicationInput publicationInput) throws Exception {

                return null;
            }
        };
    }

    @Bean
    protected ItemWriter<PublicationInput> writer() {

        return new ItemWriter<PublicationInput>() {
            @Override
            public void write(List<? extends PublicationInput> list) throws Exception {

            }
        };
    }
}
