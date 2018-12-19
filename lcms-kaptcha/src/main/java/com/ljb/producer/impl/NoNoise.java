package com.ljb.producer.impl;

import com.ljb.producer.NoiseProducer;
import com.ljb.utils.Configurable;

import java.awt.image.BufferedImage;


/**
 * Imlemention of NoiseProducer that does nothing.
 *
 * @author Yuxing Wang
 */
public class NoNoise extends Configurable implements NoiseProducer
{
	/**
	 */
	public void makeNoise(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour)
	{
		//Do nothing.
	}
}
