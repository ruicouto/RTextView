RTextView
=========

My custom TextView to support fonts as icons.

Using fonts as icons in your applications have several advantages. Briefly, the fonts are vectorial images, which support is dubious in the Android platform. So, its quality is allways high despite their size, and their footprint is very small (only a few kbs for a large set of icons).
On the downside, they are hard to use by code (leading to this library), and you can only use one color in an icon.

I've developed this library to help me using fonts as icons in my Android applications.
This is just a simple custom view which extends a TextView and adds custom font properties.

##Usage

1. Import the this library project into your project (I'm using eclipse).
2. Add your font (icons) to the assets/fonts/ folder.
3. Add an RTextView to your layout.
4. Specify the font name: app:typeface="name", where "name" is the name of your font file without the extension (i.e. name.ttf). If your file is named font.ttf, then add app:typeface="font".
5. Specify a placeholder: app:placeholder="+". There is a bug in the layout visual editor which prevents the actual font for being displayed. So, in order to help in the development process, I've added the placeholder to ease the design process.
6. Finally, specify the symbol you want to add: android:text="\<hexadecimal code>". (Check you font for the hexadecimal code)

An example is as follows:

	<com.rmsc.rtextview.RTextView
        android:id="@+id/rTextView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="\uE601"
        android:textColor="#74FFB0"
        android:textSize="50sp"
        app:placeholder="+"
        app:typeface="font" />
        
##Events

As an extension of the TextView (therefore, View class), you can add events as in any other View:

	findViewById(R.id.rTextView1).setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Toast.makeText(getApplicationContext(), "Hello!", Toast.LENGTH_LONG).show();
		}
	});

##Extra

Check <http://http://fontello.com/> or <https://icomoon.io/> to get icons as a font.

