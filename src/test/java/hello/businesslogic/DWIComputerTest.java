package hello.businesslogic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

public class DWIComputerTest {

	/** We do not need no fucking Spring to test the business logic ! */

	@Test
	public void testDWIComputerWithRealDependencies() {
		DWIComputer dwiComputer = new DWIComputer(new VolumeService(), new FiberDetector());
		int dwi = dwiComputer.computeDWI().intValue();
		Assert.assertEquals(dwi, 1 + 2 + 3);
	}

	@Test
	public void testDWIComputerWithMockedVolumeService2() {
		VolumeService volumeServiceMock = mock(VolumeService.class);
		when(volumeServiceMock.fetchVolume())
				.thenReturn(new int[] { 2, 2, 2 });

		DWIComputer dwiComputer = new DWIComputer(volumeServiceMock, new FiberDetector());

		int dwi = dwiComputer.computeDWI().intValue();
		Assert.assertEquals(dwi, 2 + 2 + 2);
	}
}
