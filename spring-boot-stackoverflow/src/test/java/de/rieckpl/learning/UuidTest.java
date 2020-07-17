package de.rieckpl.learning;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.mockito.Mockito.mockStatic;

public class UuidTest {

  @Test
  public void testUuid() {
    UUID result = UUID.randomUUID();
    MockedStatic<UUID> mocked = mockStatic(UUID.class);

    mocked.when(UUID::randomUUID).thenReturn(result);

    System.out.println(UUID.randomUUID());
    System.out.println(UUID.randomUUID());

  }
}
