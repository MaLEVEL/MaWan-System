package com.example.donormanagement.service;

import com.example.donormanagement.dto.CreateDonorRequest;
import com.example.donormanagement.dto.DonorResponse;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.exception.BusinessException;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.DonorMapper;
import com.example.donormanagement.repository.DonorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DonorService
 */
@ExtendWith(MockitoExtension.class)
public class DonorServiceTest {

  @Mock
  private DonorRepository donorRepository;

  @Mock
  private DonorMapper donorMapper;

  @InjectMocks
  private DonorService donorService;

  private CreateDonorRequest createDonorRequest;
  private Donor donor;
  private DonorResponse donorResponse;

  @BeforeEach
  void setUp() {
    // Setup test data
    createDonorRequest = new CreateDonorRequest();
    createDonorRequest.setFirstName("John");
    createDonorRequest.setLastName("Doe");
    createDonorRequest.setDateOfBirth(LocalDate.of(1990, 1, 1));
    createDonorRequest.setGender(Donor.Gender.MALE);
    createDonorRequest.setPassportNumber("AB123456");
    createDonorRequest.setBloodType(Donor.BloodType.II_POSITIVE);
    createDonorRequest.setPhone("1234567890");
    createDonorRequest.setEmail("john.doe@example.com");
    createDonorRequest.setAddress("123 Main St");

    donor = new Donor();
    donor.setId(1L);
    donor.setFirstName("John");
    donor.setLastName("Doe");
    donor.setDateOfBirth(LocalDate.of(1990, 1, 1));
    donor.setGender(Donor.Gender.MALE);
    donor.setPassportNumber("AB123456");
    donor.setBloodType(Donor.BloodType.II_POSITIVE);
    donor.setPhone("1234567890");
    donor.setEmail("john.doe@example.com");
    donor.setAddress("123 Main St");
    donor.setActive(true);

    donorResponse = new DonorResponse();
    donorResponse.setId(1L);
    donorResponse.setFirstName("John");
    donorResponse.setLastName("Doe");
    donorResponse.setDateOfBirth(LocalDate.of(1990, 1, 1));
    donorResponse.setGender(Donor.Gender.MALE);
    donorResponse.setPassportNumber("AB123456");
    donorResponse.setBloodType(Donor.BloodType.II_POSITIVE);
    donorResponse.setPhone("1234567890");
    donorResponse.setEmail("john.doe@example.com");
    donorResponse.setAddress("123 Main St");
    donorResponse.setActive(true);
  }

  @Test
  void testCreateDonor_Success() {
    // Given
    when(donorRepository.existsByPassportNumber("AB123456")).thenReturn(false);
    when(donorMapper.toEntity(createDonorRequest)).thenReturn(donor);
    when(donorRepository.save(any(Donor.class))).thenReturn(donor);
    when(donorMapper.toResponse(donor)).thenReturn(donorResponse);

    // When
    DonorResponse result = donorService.createDonor(createDonorRequest);

    // Then
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
    assertEquals("AB123456", result.getPassportNumber());

    verify(donorRepository, times(1)).existsByPassportNumber("AB123456");
    verify(donorMapper, times(1)).toEntity(createDonorRequest);
    verify(donorRepository, times(1)).save(donor);
    verify(donorMapper, times(1)).toResponse(donor);
  }

  @Test
  void testCreateDonor_PassportAlreadyExists() {
    // Given
    when(donorRepository.existsByPassportNumber("AB123456")).thenReturn(true);

    // When & Then
    BusinessException exception = assertThrows(BusinessException.class,
        () -> donorService.createDonor(createDonorRequest));

    assertEquals("PASSPORT_EXISTS", exception.getErrorCode());
    assertTrue(exception.getMessage().contains("Passport number already exists"));

    verify(donorRepository, times(1)).existsByPassportNumber("AB123456");
    verify(donorMapper, never()).toEntity(any());
    verify(donorRepository, never()).save(any());
    verify(donorMapper, never()).toResponse(any());
  }

  @Test
  void testGetDonorById_Success() {
    // Given
    when(donorRepository.findById(1L)).thenReturn(Optional.of(donor));
    when(donorMapper.toResponse(donor)).thenReturn(donorResponse);

    // When
    DonorResponse result = donorService.getDonorById(1L);

    // Then
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("John", result.getFirstName());

    verify(donorRepository, times(1)).findById(1L);
    verify(donorMapper, times(1)).toResponse(donor);
  }

  @Test
  void testGetDonorById_NotFound() {
    // Given
    when(donorRepository.findById(1L)).thenReturn(Optional.empty());

    // When & Then
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> donorService.getDonorById(1L));

    assertTrue(exception.getMessage().contains("Donor"));
    assertTrue(exception.getMessage().contains("id"));
    assertTrue(exception.getMessage().contains("1"));

    verify(donorRepository, times(1)).findById(1L);
    verify(donorMapper, never()).toResponse(any());
  }

  @Test
  void testDeleteDonor_Success() {
    // Given
    when(donorRepository.findById(1L)).thenReturn(Optional.of(donor));
    when(donorRepository.save(donor)).thenReturn(donor);

    // When
    donorService.deleteDonor(1L);

    // Then
    assertFalse(donor.getActive());
    verify(donorRepository, times(1)).findById(1L);
    verify(donorRepository, times(1)).save(donor);
  }

  @Test
  void testSearchDonors_Success() {
    // Given
    Pageable pageable = PageRequest.of(0, 10);
    List<Donor> donorList = Arrays.asList(donor);
    Page<Donor> donorPage = new PageImpl<>(donorList, pageable, 1);

    when(donorRepository.searchDonors(null, null, null, pageable))
        .thenReturn(donorPage);
    when(donorMapper.toResponse(donor)).thenReturn(donorResponse);

    // When
    Page<DonorResponse> result = donorService.searchDonors(null, null, null, pageable);

    // Then
    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    assertEquals(1, result.getContent().size());

    DonorResponse response = result.getContent().get(0);
    assertEquals(1L, response.getId());
    assertEquals("John", response.getFirstName());

    verify(donorRepository, times(1))
        .searchDonors(null, null, null, pageable);
    verify(donorMapper, times(1)).toResponse(donor);
  }

  @Test
  void testIsAdultValidation_Pass() {
    // Given
    CreateDonorRequest adultRequest = new CreateDonorRequest();
    adultRequest.setDateOfBirth(LocalDate.now().minusYears(20));

    // When & Then
    assertTrue(adultRequest.isAdult());
  }

  @Test
  void testIsAdultValidation_Fail() {
    // Given
    CreateDonorRequest childRequest = new CreateDonorRequest();
    childRequest.setDateOfBirth(LocalDate.now().minusYears(10));

    // When & Then
    assertFalse(childRequest.isAdult());
  }
}
