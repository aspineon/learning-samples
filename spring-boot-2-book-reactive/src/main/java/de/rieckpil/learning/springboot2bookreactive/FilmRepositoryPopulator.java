package de.rieckpil.learning.springboot2bookreactive;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class FilmRepositoryPopulator implements CommandLineRunner {

    static final Logger LOG = LoggerFactory
            .getLogger(FilmRepositoryPopulator.class);

    private final ResourceLoader resourceLoader;

    private final ObjectMapper objectMapper;

    private final FilmRepository filmRepository;

    public FilmRepositoryPopulator(ResourceLoader resourceLoader, ObjectMapper objectMapper, FilmRepository filmRepository) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
        this.filmRepository = filmRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        final Film[] films = loadFilms();

        this.filmRepository
                .deleteAll()
                .thenMany(Flux.just(films))
                .flatMap(f -> this.filmRepository.save(f))
                .doOnNext(f ->
                        LOG.info("Film '{}' (id={}) saved",
                                f.getTitle(), f.getId()))
                .blockLast();
    }

    @Scheduled(fixedRate = 5000)
    public void addNewFilm() {

        LOG.info("Scheduled!");

        Film film = new Film("Scheduled Film", ThreadLocalRandom.current().nextInt(1900, Year.now().getValue()));

        filmRepository.save(film);

    }

    private Film[] loadFilms() throws IOException {
        return this.objectMapper.readValue(
                this.resourceLoader.getResource("classpath:/data.json").getInputStream(),
                Film[].class);
    }
}