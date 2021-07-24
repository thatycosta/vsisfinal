package com.example.vsis3.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vsis3.entity.TeamMemberEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TeamMemberDAO {
    @Query("SELECT * FROM TeamMemberEntity")
    public List<TeamMemberEntity> getAllMembers();

    @Query("SELECT * FROM TeamMemberEntity WHERE name = :name")
    public List<TeamMemberEntity> getTeamMemberByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(TeamMemberEntity teamMemberEntity);

    @Update
    public void update(TeamMemberEntity teamMemberEntity);

    @Delete
    public void delete(TeamMemberEntity teamMemberEntity);
}
