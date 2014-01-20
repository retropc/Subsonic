/*
 This file is part of Subsonic.

 Subsonic is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Subsonic is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Subsonic.  If not, see <http://www.gnu.org/licenses/>.

 Copyright 2009 (C) Sindre Mehus
 */
package github.daneren2005.dsub.service.parser;

import android.content.Context;
import github.daneren2005.dsub.domain.MusicDirectory;

/**
 * @author Sindre Mehus
 */
public class MusicDirectoryEntryParser extends AbstractParser {
    public MusicDirectoryEntryParser(Context context) {
        super(context);
    }

    protected MusicDirectory.Entry parseEntry(String artist) {
        MusicDirectory.Entry entry = new MusicDirectory.Entry();
        entry.setId(get("id"));
        entry.setParent(get("parent"));
		if(entry.getParent() == null) {
			entry.setParent(get("albumId"));
			if(entry.getParent() == null) {
				entry.setParent(get("artistId"));
			}
		}
        entry.setTitle(get("title"));
		if(entry.getTitle() == null) {
			entry.setTitle(get("name"));
		}
        entry.setDirectory(getBoolean("isDir"));
        entry.setCoverArt(get("coverArt"));
        entry.setArtist(get("artist"));
        entry.setStarred(get("starred") != null);
        entry.setYear(getInteger("year"));
        entry.setGenre(get("genre"));

        if (!entry.isDirectory()) {
            entry.setAlbum(get("album"));
            entry.setTrack(getInteger("track"));
            entry.setContentType(get("contentType"));
            entry.setSuffix(get("suffix"));
            entry.setTranscodedContentType(get("transcodedContentType"));
            entry.setTranscodedSuffix(get("transcodedSuffix"));
            entry.setSize(getLong("size"));
            entry.setDuration(getInteger("duration"));
            entry.setBitRate(getInteger("bitRate"));
            entry.setPath(get("path"));
            entry.setVideo(getBoolean("isVideo"));
			entry.setDiscNumber(getInteger("discNumber"));
        } else if(!"".equals(artist)) {
			entry.setPath(artist + "/" + entry.getTitle());
		}
        return entry;
    }
	
	protected MusicDirectory.Entry parseArtist() {
		MusicDirectory.Entry entry = new MusicDirectory.Entry();
		
		entry.setId(get("id"));
		entry.setTitle(get("name"));
		entry.setPath(entry.getTitle());
		entry.setStarred(true);
		entry.setDirectory(true);
		
		return entry;
	}
}
