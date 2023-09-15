package org.example;


import org.example.framework.CrudRepository;
import org.example.framework.CrudRepositoryImp;
import org.example.model.AdArticle;
import org.example.service.AdArticleRepository;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ORMTest {
    CrudRepository<AdArticle> adArticleRepository = new CrudRepositoryImp<>();
    AdArticleRepository articleRepository = new AdArticleRepository(adArticleRepository);
    @Test
    public void testGetAdArticle() throws Exception {
        try {
            List<AdArticle> adArticleList = articleRepository.getArticle();
            assertNotNull(adArticleList);
            assertEquals(5, adArticleList.size());
        } finally {
            adArticleRepository.close();
        }
    }

    @Test
    public void testInsertAdArticle() throws Exception {
        try {
            AdArticle adArticle = new AdArticle("Property1", "Property2", "Property3", "Property4", "Property5");
            articleRepository.insertArticle(adArticle);
            List<AdArticle> adArticleList = articleRepository.getArticle();
            assertEquals(6, adArticleList.size());
        } finally {
            adArticleRepository.close();
        }
    }

    @Test
    public void testFindAdArticle() throws Exception {
        try {
            List<AdArticle> adArticleList = articleRepository.findArticle(adArticle -> adArticle.getGuid().equals("Property1"));
            assertEquals(1, adArticleList.size());
        } finally {
            adArticleRepository.close();
        }
    }

    @Test
    public void testUpdateAdArticle() throws Exception {
        try {
            AdArticle adArticle = new AdArticle("Property1", "Nguyen", "Nguyen", "Nguyen", "Nguyen");
            articleRepository.updateArticle(adArticle, adArticle1 -> adArticle1.getGuid().equals("Property1"));
            List<AdArticle> adArticleList = articleRepository.getArticle();
            assertEquals(6, adArticleList.size());
        } finally {
            adArticleRepository.close();
        }
    }
    @Test
    public void testDeleteAdArticle() throws Exception {
        try {
            articleRepository.deleteArticle(adArticle -> adArticle.getGuid().equals("Property1"));
            List<AdArticle> adArticleList = articleRepository.getArticle();
            assertEquals(5, adArticleList.size());
        } finally {
            adArticleRepository.close();
        }
    }
}