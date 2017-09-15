package tools;

import models.*;
import play.cache.Cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aazzou on 10/24/14.
 */
public class MyCacheSystem {
    // caching Members
    public static List<Member> getMembers() {
        List<Member> members = Cache.get("members", ArrayList.class);
        if(members == null){
            members = Member.all().fetch();
            Cache.set("members",members,"30mn");
        }
        return members;
    }
    public static void deleteCacheMembers(){ Cache.delete("members");   }

    public static Member getMember(Long id) {
        Member member = Cache.get("member_" + id, Member.class);
        if(member == null){
            member = Member.findById(id);
            Cache.set("member_"+id, member,"30mn");
        }
        return member;
    }
    public static void deleteCacheMember(Long id){ Cache.delete("member_"+id);}

    // caching member categories
    public static List<MemberCategory> getMemberCategories() {
        List<MemberCategory> categories = Cache.get("categories", ArrayList.class);
        if(categories == null){
            categories = MemberCategory.all().fetch();
            Cache.set("categories",categories,"30mn");
        }
        return categories;
    }
    public static void deleteCacheMemberCategories(){ Cache.delete("categories");}


    public static MemberCategory getMemberCategory(Long id) {
        MemberCategory selectedCategory = Cache.get("category_" + id, MemberCategory.class);
        if(selectedCategory == null){
            selectedCategory = MemberCategory.findById(id);
            Cache.set("category_" + id,selectedCategory,"30mn");
        }
        return selectedCategory;
    }
    public static void deleteCacheMemberCategory(Long id){ Cache.delete("category_"+id);}

    public static List<Member> getMembers(Long id, MemberCategory selectedCategory) {
        List<Member> members = Cache.get("members_by_category_" + id, ArrayList.class);
        if(members == null){
            members = Member.find("byCategory",selectedCategory).fetch();
            Cache.set("members_by_category_" + id,members,"30mn");
        }
        return members;
    }
    public static void deleteCacheMembers(Long id){ Cache.delete("members_by_category_" + id); }

    // caching news
    public static List<New> getNews() {
        List<New> news = Cache.get("news", ArrayList.class);
        if(news == null){
            news = New.all().fetch();
            Cache.set("news",news,"30mn");
        }
        return news;
    }
    public static void deleteCacheNews(){ Cache.delete("news");}


    public static List<Section> getSections() {
        List<Section> sections = Cache.get("sections", ArrayList.class);
        if(sections == null){
            sections = Section.all().fetch();
            Cache.set("sections",sections,"30mn");
        }
        return sections;
    }
    public static void deleteCacheSections(){ Cache.delete("sections");}

    public static Section getSection(Long id) {
        Section selectedSection = Cache.get("section_" + id, Section.class);
        if(selectedSection == null){
            selectedSection = Section.findById(id);
            Cache.set("section_"+ id,selectedSection,"30mn");
        }
        return selectedSection;
    }
    public static void deleteCacheSection(Long id){ Cache.delete("section_" + id);}

    public static New getNew(Long id) {
        New _new = Cache.get("new_" + id, New.class);
        if(_new == null){
            _new = New.findById(id);
            Cache.set("new_"+ id,_new,"30mn");
        }
        return _new;
    }
    public static void deleteCacheNew(Long id){ Cache.delete("new_" + id);}

    // caching research areas
    public static List<ResearchArea> getResearchAreas() {
        List<ResearchArea> researchAreas = Cache.get("researchAreas", ArrayList.class);
        if(researchAreas == null){
            researchAreas = ResearchArea.all().fetch();
            Cache.set("researchAreas",researchAreas,"30mn");
        }
        return researchAreas;
    }
    public static void deleteCacheResearchAreas(){ Cache.delete("researchAreas");}

    public static ResearchArea getResearchArea(Long id) {
        ResearchArea researchArea = Cache.get("researchArea_"+id, ResearchArea.class);
        if(researchArea == null){
            researchArea = ResearchArea.findById(id);
            Cache.set("researchArea_"+id,researchArea,"30mn");
        }
        return researchArea;
    }
    public static void deleteCacheResearchArea(Long id){ Cache.delete("researchArea_"+ id);}

    // caching projects
    public static List<Project> getProjects() {
        List<Project> projects = Cache.get("projects", ArrayList.class);
        if(projects == null){
            projects = Project.all().fetch();
            Cache.set("projects",projects,"30mn");
        }
        return projects;
    }
    public static void deleteCacheProjects(){ Cache.delete("projects");}

    public static Project getProject(Long id) {
        Project project = Cache.get("project_"+id, Project.class);
        if(project == null){
            project = Project.findById(id);
            Cache.set("project_"+id,project,"30mn");
        }
        return project;
    }
    public static void deleteCacheProject(Long id){ Cache.delete("project_"+ id);}

    // caching publications
    public static List<Publication> getPublications() {
        List<Publication> publications = Cache.get("publications", ArrayList.class);
        if(publications == null){
            publications = Publication.all().fetch();
            Cache.set("publications",publications,"30mn");
        }
        return publications;
    }
    public static void deleteCachePublications(){ Cache.delete("publications");}

    public static Publication getPublication(Long id) {
        Publication publication = Cache.get("publication_" + id, Publication.class);
        if(publication == null){
            publication = Publication.findById(id);
            Cache.set("publication_" + id,publication,"30mn");
        }
        return publication;
    }
    public static void deleteCachePublication(Long id){ Cache.delete("publication_"+id);}
    // Admin section cache

    // cache and retrieve objects
    public static Map<String, Long> getStatistics() {
        Map<String,Long> statistics = Cache.get("statistics", HashMap.class);
        if(statistics == null){
            statistics = new HashMap<String, Long>();
            statistics.put("membersCount", Member.count());
            statistics.put("researchAreasCount", ResearchArea.count());
            statistics.put("projectsCount", Project.count());
            statistics.put("publicationsCount", Publication.count());
            statistics.put("newsCount", New.count());
            statistics.put("eventsCount",Event.count());
            Cache.set("statistics",statistics,"30mn");
        }
        return statistics;
    }
    public static void deleteCacheStatistics(){ Cache.delete("statistics");}

    public static List<Event> getLastEvents(int size) {
        List<Event> lastEvents = Cache.get("lastEvents", ArrayList.class);
        if(lastEvents == null){
            lastEvents = Event.all().fetch(size);
            Cache.set("lastEvents",lastEvents,"30mn");
        }
        return lastEvents;
    }
    public static void deleteCacheLastEvents(){ Cache.delete("lastEvents");}

    public static List<New> getLastNews(int size) {
        List<New> lastNews = Cache.get("lastNews", ArrayList.class);
        if(lastNews == null){
            lastNews = New.all().fetch(size);
            Cache.set("lastNews",lastNews,"30mn");
        }
        return lastNews;
    }
    public static void deleteCacheLastNews(){ Cache.delete("lastNews");}

    public static List<Publication> getLastPublications(int size) {
        List<Publication> lastPublications = Cache.get("lastPublications", ArrayList.class);
        if(lastPublications == null){
            lastPublications = Publication.all().fetch(size);
            Cache.set("lastPublications",lastPublications,"30mn");
        }
        return lastPublications;
    }
    public static void deleteCacheLastPublications(){Cache.delete("lastPublications");}

    public static List<Project> getLastProjects(int size) {
        List<Project> lastProjects = Cache.get("lastProjects", ArrayList.class);
        if(lastProjects == null){
            lastProjects = Project.all().fetch(size);
            Cache.set("lastProjects",lastProjects,"30mn");
        }
        return lastProjects;
    }
    public static void deleteCacheLastProjects(){Cache.delete("lastProjects");}

    public static List<ResearchArea> getLastResearchAreas(int size) {
        List<ResearchArea> lastResearchAreas = Cache.get("lastResearchAreas", ArrayList.class);
        if(lastResearchAreas == null){
            lastResearchAreas =  ResearchArea.all().fetch(size);
            Cache.set("lastResearchAreas",lastResearchAreas,"30mn");
        }
        return lastResearchAreas;
    }
    public static void deleteCacheLastResearchAreas(){Cache.delete("lastResearchAreas");}

    public static List<Member> getLastMembers(int size) {
        List<Member> lastMembers = Cache.get("lastMembers", ArrayList.class);
        if(lastMembers == null){
            lastMembers = Member.all().fetch(size);
            Cache.set("lastMembers",lastMembers,"30mn");
        }
        return lastMembers;
    }
    public static void deleteCacheLastMembers(){Cache.delete("lastMembers");}
    // about cache
    public static About getAbout() {
        About about = Cache.get("about", About.class);
        if(about == null){
            about = About.all().first();
            Cache.set("about",about,"30mn");
        }
        return about;
    }
    public static void deleteCacheAbout(){Cache.delete("about");}

    // others
    public static Map<Long, Long> getMemberCategoryCount(List<MemberCategory> categories) {
        Map<Long,Long> catCount = Cache.get("members_category_count", HashMap.class);

        if(catCount == null){
            catCount = new HashMap<Long, Long>();
            for(MemberCategory cat : categories){
                catCount.put(cat.id, Member.count("category = ?", cat));
            }
            Cache.set("members_category_count",catCount,"30min");
        }
        return catCount;
    }
    public static void deleteCacheMemberCategoryCount(){Cache.delete("members_category_count");}
    public static Map<Long, Long> getProjectsPerResAreaCount(List<ResearchArea> researchAreas) {
        Map<Long,Long> catCount = Cache.get("projects_researcharea_count", HashMap.class);
        if(catCount == null){
            catCount = new HashMap<Long, Long>();
            for(ResearchArea rcat : researchAreas){
                catCount.put(rcat.id, Project.count("researchArea = ?", rcat));
            }
            Cache.set("projects_researcharea_count",catCount,"30mn");
        }
        return catCount;
    }
    public static void deleteCacheProjectsPerResAreaCount(){Cache.delete("projects_researcharea_count");}

    public static void deleteMemberAttachedCache(Member member){
        deleteCacheLastMembers();
        deleteCacheMemberCategories();
        deleteCacheMember(member.id);
        deleteCacheMemberCategoryCount();
        deleteCacheMembers();
        deleteCacheMembers(member.category.id);
        deleteCacheMemberCategory(member.category.id);
    }

    public static void deleteMemberCategoryAttachedCache(MemberCategory category){
        deleteCacheMemberCategoryCount();
        deleteCacheMemberCategory(category.id);
        deleteCacheMemberCategories();
    }

    public static void deleteResearchAreaAttachedCache(ResearchArea researchArea){
        deleteCacheResearchArea(researchArea.id);
        deleteCacheResearchAreas();
        deleteCacheLastResearchAreas();
    }

    public static void deleteProjectAttachedCache(Project project){
        deleteCacheProject(project.id);
        deleteCacheProjects();
        deleteCacheProjectsPerResAreaCount();
        deleteCacheLastProjects();
    }

    public static void deletePublicationAttachedCache(Publication publication){
        deleteCachePublication(publication.id);
        deleteCachePublications();
        deleteCacheLastPublications();
    }

    public static void deleteNewAttachedCache(New _new){
        deleteCacheNew(_new.id);
        deleteCacheNews();
        deleteCacheLastNews();
    }

    public static void deleteSectionAttachedCache(Section section){
        deleteCacheSection(section.id);
        deleteCacheSections();
    }

}
