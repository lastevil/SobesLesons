package org.homework.repositorys;

import org.homework.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student,Long> {
}
