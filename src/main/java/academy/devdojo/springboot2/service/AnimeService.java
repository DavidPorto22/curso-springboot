package academy.devdojo.springboot2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AnimeService {
	private final AnimeRepository animeRepository;
	
	public Page<Anime> listAll(Pageable pageable) {
		return animeRepository.findAll(pageable);
	}
	
	public List<Anime> listAllNonPageable() {
		return animeRepository.findAll();
	}
	
	public List<Anime> findByName(String name) {
		return animeRepository.findByName(name);
	}
	
	public Anime findByIdOrThrowBadRequestException(long id) {
		return animeRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Anime not found"));
	}
	
	@Transactional
	public Anime save(AnimePostRequestBody animePostRequestBody){
		Anime anime = Anime.builder().name(animePostRequestBody.getName()).build();
		return animeRepository.save(anime);
	}

	public void delete(long id) {
		animeRepository.delete(findByIdOrThrowBadRequestException(id));
	}
	
	public void replace(AnimePutRequestBody animePutRequestBody) {
		Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
		Anime anime = Anime.builder()
				.id(savedAnime.getId())
				.name(animePutRequestBody.getName())
				.build();
		animeRepository.save(anime);
	}
}
















