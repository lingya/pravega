/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emc.pravega.service.server.reading;

import com.emc.pravega.service.server.ContainerMetadata;
import com.google.common.base.Preconditions;
import lombok.Getter;

/**
 * A ReadIndexEntry that points to data that was merged from a different Segment.
 */
class MergedReadIndexEntry extends ReadIndexEntry {
    /**
     * Gets a value representing the Id of the Segment that was merged.
     */
    @Getter
    private final long sourceSegmentId;
    /**
     * Gets a value representing the offset inside the SourceSegment where this data is located.
     */
    @Getter
    private final long sourceSegmentOffset;

    /**
     * Creates a new instance of the MergedReadIndexEntry class.
     *
     * @param streamSegmentOffset The StreamSegment offset for this entry.
     * @param length              The length for this Entry.
     * @param sourceSegmentId     The Id of the Segment that was merged.
     * @param sourceSegmentOffset The offset inside the SourceSegment where this data can be located.
     * @throws IllegalArgumentException If offset, length or sourceSegmentOffset are negative numbers.
     * @throws IllegalArgumentException If sourceSegmentId is invalid.
     */
    MergedReadIndexEntry(long streamSegmentOffset, long length, long sourceSegmentId, long sourceSegmentOffset) {
        super(streamSegmentOffset, length);
        Preconditions.checkArgument(sourceSegmentId != ContainerMetadata.NO_STREAM_SEGMENT_ID, "sourceSegmentId");
        Preconditions.checkArgument(sourceSegmentOffset >= 0, "streamSegmentOffset must be a non-negative number.");

        this.sourceSegmentId = sourceSegmentId;
        this.sourceSegmentOffset = sourceSegmentOffset;
    }
}