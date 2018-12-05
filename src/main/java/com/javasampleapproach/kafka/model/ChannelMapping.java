/*
 * @(#)ChannelMapping.java
 *
 * Copyright (c) 2018 Lufthansa Industry Solutions . All Rights Reserved.
 *
 * LastChangedDate: Dec 5, 2018
 * LastChangedRevision: 1.0
 * LastChangedBy: U595908
 *
 */
package com.javasampleapproach.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ChannelMapping.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelMapping {
	/** The id. */
	private String channelName;

	/** The channel indicator. */
	private String channelIndicator;
}
