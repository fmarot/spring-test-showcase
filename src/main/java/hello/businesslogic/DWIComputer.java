package hello.businesslogic;

import java.util.Arrays;

public class DWIComputer {

	private VolumeService volumeService;
	private FiberDetector fiberDetector;

	public DWIComputer(VolumeService volumeService, FiberDetector fiberDetector) {
		this.volumeService = volumeService;
		this.fiberDetector = fiberDetector;
	}
	
	public Integer computeDWI() {
		int[] volume = volumeService.fetchVolume();
		int[] fibers = fiberDetector.detectFibers(volume);
		int dwi = Arrays.stream(fibers).sum();
		return dwi;
	}
}
