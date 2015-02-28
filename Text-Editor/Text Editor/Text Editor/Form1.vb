Public Class Form1

    Dim myFormat As DataFormats.Format = DataFormats.GetFormat(DataFormats.Rtf)
    Dim filePath As String = "new_File"
    Dim fileName As String = "new_File"
    Dim RTBnew As RichTextBox
    Dim RTBcurrent As RichTextBox
    Dim initialTab As Boolean = True


    Public Sub SaveForm()
        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        SaveFileDialog1.DefaultExt = "rtf"
        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        Dim result As DialogResult = SaveFileDialog1.ShowDialog()
        If (result = Windows.Forms.DialogResult.OK) Then
            filePath = SaveFileDialog1.FileName
            fileName = filePath.Substring(filePath.LastIndexOf("\") + 1)
            RTBcurrent.SaveFile(SaveFileDialog1.FileName)
            RTBcurrent.Name = filePath
            TabControl1.SelectedTab.Text = fileName
            TabControl1.SelectedTab.Update()
        End If
    End Sub


    Private Sub Form1_Load(sender As System.Object, e As System.EventArgs) Handles MyBase.Load
    End Sub

    Private Sub CopyToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles CopyToolStripMenuItem.Click, CopyToolStripMenuItem1.Click
        ' RichTextBox1.Copy()
        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        RTBcurrent.Copy()
    End Sub

    Private Sub PasteToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles PasteToolStripMenuItem.Click, PasteToolStripMenuItem1.Click
        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        If (RTBcurrent.CanPaste(myFormat) = True) Then
            ' RichTextBox1.Paste()
            RTBcurrent.Paste()

        End If

    End Sub

    Private Sub CutToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles CutToolStripMenuItem.Click, CutToolStripMenuItem1.Click
        ' RichTextBox1.Cut()

        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        RTBcurrent.Cut()

    End Sub

    Private Sub SaveAsToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles SaveAsToolStripMenuItem.Click
        ' Save as new file
        SaveForm()

    End Sub

    Private Sub SaveToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles SaveToolStripMenuItem.Click
        ' Save File, Overwrites if the file has a name other than the default (ie, previously saved)

        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        If (RTBcurrent.Name = "new_File") Then
            SaveForm()
        Else
            RTBcurrent.SaveFile(RTBcurrent.Name)
        End If

    End Sub

    Private Sub OpenToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles OpenToolStripMenuItem.Click
        ' Open File Dialog
        OpenFileDialog1.FileName = ""    ' Clear File Name Input

        Dim result As DialogResult = OpenFileDialog1.ShowDialog()
        If (result = Windows.Forms.DialogResult.OK) Then
            ' Check that file exists
            If (OpenFileDialog1.CheckFileExists = True) Then
                If (initialTab = True) Then     'Get rid of the initial tab
                    TabControl1.SelectTab(0)
                    TabControl1.SelectedTab.Dispose()
                    initialTab = False
                End If

                filePath = OpenFileDialog1.FileName
                fileName = filePath.Substring(filePath.LastIndexOf("\") + 1)
                ToolStripStatusLabel1.Text = filePath

                TabControl1.TabPages.Add(fileName)
                TabControl1.SelectTab(TabControl1.TabPages.Count - 1)
                RTBnew = New RichTextBox
                RTBnew.Name = filePath
                RTBnew.Anchor = AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right Or AnchorStyles.Top
                RTBnew.Size = TabControl1.SelectedTab.Size
                RTBnew.ContextMenuStrip = ContextMenuStrip1
                Controls.Add(RTBnew)
                TabControl1.SelectedTab.Controls.Add(RTBnew)
                RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
                RTBcurrent.LoadFile(filePath)

            Else

            End If
        End If

    End Sub

    Private Sub ExitToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles ExitToolStripMenuItem.Click
        ' Close Program
        ' Display "Are You sure Window"
        Me.Close()
    End Sub

    Private Sub NewToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles NewToolStripMenuItem.Click
        ' Open new tab, default name is new_File
        If (initialTab = True) Then     'Get rid of the initial tab
            TabControl1.SelectTab(0)
            TabControl1.SelectedTab.Dispose()
            initialTab = False
        End If

        TabControl1.TabPages.Add("new_File")
        TabControl1.SelectTab(TabControl1.TabPages.Count - 1)
        RTBnew = New RichTextBox
        RTBnew.Name = "new_File"
        RTBnew.Anchor = AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right Or AnchorStyles.Top
        RTBnew.Size = TabControl1.SelectedTab.Size
        RTBnew.ContextMenuStrip = ContextMenuStrip1
        Controls.Add(RTBnew)
        TabControl1.SelectedTab.Controls.Add(RTBnew)

    End Sub

    Private Sub CloseToolStripMenuItem_Click(sender As System.Object, e As System.EventArgs) Handles CloseToolStripMenuItem.Click
        ' Close current tab
        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
        'If (RTBcurrent.Modified = True) Then
        '    SaveForm()
        'Else
        '    RTBcurrent.Dispose()
        'End If

        RTBcurrent.Dispose()

    End Sub

    Private Sub TabControl1_MouseDown(sender As System.Object, e As System.Windows.Forms.MouseEventArgs) Handles TabControl1.MouseDown
        RTBcurrent = TabControl1.SelectedTab.Controls.Item(0)
    End Sub
End Class


' http://www.codeproject.com/Articles/15562/Reordering-TabPages-inside-TabControl